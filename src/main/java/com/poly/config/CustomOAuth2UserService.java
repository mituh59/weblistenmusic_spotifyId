package com.poly.config;

import com.poly.dao.RolesDAO;
import com.poly.dao.UsersDAO;
import com.poly.entity.Roles;
import com.poly.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private RolesDAO rolesDAO;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService()
                .loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");

        // Kiểm tra xem người dùng đã tồn tại chưa
        Optional<Users> optionalUser = usersDAO.findById(email);
        Users user;

        if (optionalUser.isEmpty()) {
            // Nếu chưa có thì tạo người dùng mới
            user = new Users();
            user.setUsername(email);       // Dùng email làm username
            user.setPassword("");          // Để trống mật khẩu (vì đăng nhập Google)
            user.setHoTen(name);           // Tên lấy từ Google
            user.setEmail(email);          // Email lấy từ Google
            user.setHinhAnh(picture);      // Ảnh đại diện lấy từ Google
            user.setTrangThai(true);       // Trạng thái active

            // Thiết lập role mặc định là "ROLE_USER"
            Roles defaultRole = rolesDAO.findById("ROLE_USER").orElse(null);
            if (defaultRole == null) {
                // Nếu role chưa tồn tại, tự động tạo role mới
                defaultRole = new Roles();
                defaultRole.setRoleId("ROLE_USER");
                defaultRole.setTenRole("Người dùng");
                rolesDAO.save(defaultRole);
            }

            user.setRole(defaultRole);

            try {
                // Lưu vào cơ sở dữ liệu
                usersDAO.save(user);
            } catch (Exception e) {
                throw new OAuth2AuthenticationException("Error saving new user: " + e.getMessage());
            }
        } else {
            // Nếu người dùng đã tồn tại
            user = optionalUser.get();
        }

        // Trả về đối tượng OAuth2User với quyền hạn
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRoleId())),
                oAuth2User.getAttributes(),
                "email" // Thuộc tính chính định danh người dùng
        );
    }
}
