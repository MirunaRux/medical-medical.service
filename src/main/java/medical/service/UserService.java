package medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import medical.dao.UserDao;
import medical.model.User;

@Service
public class UserService {
    private static List<User> users;

    @Autowired
    private UserDao userDao;

    public List<User> findAllUsers() {
        users = userDao.getAllUsers();
        return users;
    }

    public User createUser(User user) {
        userDao.create(user);
        return user;
    }
}
