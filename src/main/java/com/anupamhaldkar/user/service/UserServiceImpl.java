package com.anupamhaldkar.user.service;

import com.anupamhaldkar.user.entity.UserEntity;
import com.anupamhaldkar.user.model.User;
import com.anupamhaldkar.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
      List<UserEntity> userEntities = userRepository.findAll();
      List<User> users = userEntities.stream().map(userEntity -> {
          User user = new User();
          BeanUtils.copyProperties(userEntity, user);
          return user;
      }).toList();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public Boolean deleteUser(Long id) {
       UserEntity userEntity = userRepository.findById(id).get();
       userRepository.delete(userEntity);
       return true;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setEmailId(user.getEmailId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userRepository.save(userEntity);
                return user;
    }
}
