package com.poly.config;

import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${jwt.signerKey}")
	private String signerKey;

	@Bean
	JwtDecoder jwtDecoder() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}

	@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery(
				"SELECT username AS username, password, trangThai FROM Users WHERE username = ?");
		manager.setAuthoritiesByUsernameQuery(
				"SELECT u.username, r.tenRole FROM Users u JOIN Roles r ON u.roleId = r.roleId WHERE u.username = ?");
		return manager;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());

		http.authorizeHttpRequests(request -> request.requestMatchers("/static/**", "/css/**", "/js/**",
				"/user/images/**").permitAll()
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers("/manager/**").hasAuthority("ROLE_MANAGER")
				.requestMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER")
				.anyRequest().permitAll());

		// Cấu hình logout
		http.logout().logoutUrl("/logout") // Cấu hình URL cho logout
				.logoutSuccessUrl("/login?logout=true") // Chuyển hướng khi logout thành công
				.invalidateHttpSession(true) // Xóa session sau khi logout
				.clearAuthentication(true) // Xóa thông tin xác thực
				.deleteCookies("JSESSIONID", "SESSION").addLogoutHandler((request, response, authentication) -> {
					SecurityContextHolder.clearContext();
					Cookie cookie = new Cookie("access_token", null);
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					Cookie refreshToken = new Cookie("refresh_token", null);
					refreshToken.setMaxAge(0);
					refreshToken.setPath("/");
					response.addCookie(refreshToken);
				});

		http.exceptionHandling(denied -> denied.accessDeniedPage("/"));

		// Xử lý đăng nhập thành công (cho cả username/password và OAuth2)
		http.formLogin(
				login -> login.loginPage("/login").permitAll().successHandler((request, response, authentication) -> {
					authentication.getAuthorities().forEach(auth -> {
						try {
							if (auth.getAuthority().equals("ROLE_ADMIN")) {
								response.sendRedirect("/admin/theloai/index.html");
							} else if (auth.getAuthority().equals("ROLE_MANAGER")) {
								response.sendRedirect("/manager/theloai/index.html");
							} else if (auth.getAuthority().equals("ROLE_USER")) {
								response.sendRedirect("/view/trangchu.html");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				}));

		// 👉 **Thêm OAuth2 Login (Google)**
		http.oauth2Login(oauth2 -> oauth2.loginPage("/login") // Trang đăng nhập
				.defaultSuccessUrl("/view/trangchu.html", true) // Điều hướng sau khi đăng nhập thành công
				.failureUrl("/login?error=true") // Chuyển hướng nếu lỗi
		);

		// Cấu hình OAuth2 Resource Server
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
				.jwtAuthenticationConverter(jwtAuthenticationConverter())));

		// Cấu hình session
		http.sessionManagement().invalidSessionUrl("/login?sessionExpired=true").maximumSessions(1)
				.expiredUrl("/login?sessionExpired=true");

		return http.build();
	}

	@Bean
	public SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository() {
			@Override
			public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
				if (context == null) {
					SecurityContextHolder.clearContext();
				}
				super.saveContext(context, request, response);
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
