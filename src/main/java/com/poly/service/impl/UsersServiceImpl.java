package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dao.UsersDAO;
import com.poly.entity.Users;
import com.poly.service.UsersService;

@Service("UsersService")
public class UsersServiceImpl implements UsersService{

	@Autowired
	UsersDAO dao;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Users findById(String id) {
		Optional<Users> userOptional = dao.findById(id);

	    // Kiểm tra nếu người dùng tồn tại, nếu không trả về một đối tượng Users rỗng
	    return userOptional.orElse(new Users());
	}

    @Override
    public Users create(Users entity) {
        // Mã hóa mật khẩu trước khi lưu
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return dao.save(entity);
    }

    @Override
    public void update(Users entity) {
        // Nếu mật khẩu đã được mã hóa trước đó, không mã hóa lại
        Users existingUser = dao.findById(entity.getUsername()).orElse(null);
        if (existingUser != null) {
            if (!entity.getPassword().equals(existingUser.getPassword())) {
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            }
        }
        dao.save(entity);
    }

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}
   
}
