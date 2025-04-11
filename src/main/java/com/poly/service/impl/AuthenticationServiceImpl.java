package com.poly.service.impl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dao.*;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.poly.dto.request.AuthenticationRequest;
import com.poly.dto.request.IntrospectRequest;
import com.poly.dto.response.AuthenticationResponse;
import com.poly.dto.response.IntrospectResponse;
import com.poly.entity.Users;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service("authService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthenticationServiceImpl {
	@Autowired
	UsersDAO userDao;
	@Autowired
	PasswordEncoder passwordEncoder;

	@NonFinal
	@Value("${jwt.signerKey}")
	protected String SIGNER_KEY;

	public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
		var token = request.getToken();

		JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

		SignedJWT signedJWT = SignedJWT.parse(token);

		Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

		var verified = signedJWT.verify(verifier);

		return IntrospectResponse.builder().valid(verified && expityTime.after(new Date())).build();

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
		Users user = userDao.findById(request.getUsername()).orElseThrow(() -> new Exception("Invalid credentials"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new Exception("Invalid credentials");
		}

		String token = generateToken(user);

		return AuthenticationResponse.builder().token(token).authenticated(true).build();
	}

	private String generateToken(Users user) {
		try {
			JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
			JWTClaimsSet claims = new JWTClaimsSet.Builder().subject(user.getUsername()).issuer("@nguyenlee")
					.issueTime(new Date()).expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS))) // Thay
																												// thế
																												// cách
																												// cũ
					.claim("scope", user.getRole().getTenRole().replaceFirst("ROLE_", "")).build();

			JWSObject jwsObject = new JWSObject(header, new Payload(claims.toJSONObject()));
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

			return jwsObject.serialize();
		} catch (JOSEException e) {
			throw new RuntimeException("Lỗi khi ký JWT", e);
		}
	}

	private String buildScope(Users user) {
		StringJoiner stringJoiner = new StringJoiner(" "); // Tạo StringJoiner với dấu cách là phân tách giữa các vai
															// trò

		// Kiểm tra xem user có roleName không rỗng
		if (user != null && user.getRole() != null && user.getRole().getTenRole() != null) {
			// Thêm roleName vào stringJoiner nếu có
			stringJoiner.add(user.getRole().getTenRole().substring(5));
		}

		// Trả về chuỗi các scope
		return stringJoiner.toString();
	}

}
