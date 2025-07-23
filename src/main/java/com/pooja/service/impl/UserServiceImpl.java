package com.pooja.service.impl;

import com.pooja.model.User;
import com.pooja.repository.UserRepository;
import com.pooja.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User saveUser(User user) {
       repo.save(convertModel2Entity(user));
        return user;
    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<com.pooja.entity.User> userEntity = repo.findById(id);
        User userModel = new User();
        convertEntity2Model(userModel, userEntity.orElse(null));

        return Optional.of(userModel);
    }

    @Override
    public List<User> getAllUsers() {
        List<com.pooja.entity.User> users = repo.findAll();
        List<User> userModelList = new ArrayList<User>();
        for (int i=0; i< users.size(); i++)
        {
            User userModel = new User();
            convertEntity2Model(userModel, users.get(i));
            userModelList.add(userModel);
        }

        // fetch all
        // iterate all
            // convert type
            // add in new required list which we have to return
        return userModelList;
    }

    @Override
    public User updateUser(String id, User user) {
//        user = repo.findById(id);
//        com.pooja.entity.User userEntity = convertModel2Entity(user);

            if(repo.existsById(id)){
                repo.save(convertModel2Entity(user));
                return user;
            }
        return null;
    }

    @Override
    public void deleteUser(String id) {
        repo.deleteById(id);
    }

    private static void convertEntity2Model(User userModel, com.pooja.entity.User userEntity) {
        if (userEntity != null) {

            userModel.setAge(userEntity.getAge());
            userModel.setId(userEntity.getId());
            userModel.setEmail(userEntity.getEmail());
            userModel.setName(userEntity.getName());
            userModel.setUsername(userEntity.getUserName());
        }
    }

    private static com.pooja.entity.User convertModel2Entity(User userModel) {
        com.pooja.entity.User userEntity = new com.pooja.entity.User();
        userEntity.setAge(userModel.getAge());
        userEntity.setId(userModel.getId());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setName(userModel.getName());
        userEntity.setUserName(userModel.getUsername());
        return userEntity;
    }
}
