package com.kieran.api.service;


import com.kieran.api.dao.queries.UserDetailsRepository;
import com.kieran.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserInfoService {

    @Autowired
    private UserDetailsRepository userDatailsRepository;

    public UserInfo getUserInfoByUserName(String userName) {
        short enabled = 1;
        return userDatailsRepository.findByUserNameAndEnabled(userName, enabled);
    }

    public List<UserInfo> getAllActiveUserInfo() {
        return userDatailsRepository.findAllByEnabled((short) 1);
    }

    public UserInfo getUserInfoById(Integer id) {
        return userDatailsRepository.findById(id);
    }

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        return userDatailsRepository.save(userInfo);
    }

    public UserInfo updateUser(UserInfo userInfo) {
        return userDatailsRepository.save(userInfo);
    }

    public void deleteUser(Integer id) {
        userDatailsRepository.deleteById(id);
    }
}
