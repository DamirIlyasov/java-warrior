package ru.itis.javawarrior.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javawarrior.db.dao.BaseDAO;
import ru.itis.javawarrior.db.model.User;
import ru.itis.javawarrior.db.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Long BASE_LEVEL = 1L;
    @Autowired
    private BaseDAO baseDAO;

    @Override
    public void save(User user) {
        baseDAO.save(user);
    }

    @Override
    public void delete(Long id) {
        baseDAO.delete(User.class, id);
    }

    @Override
    public User findById(Long id) {
        return baseDAO.findById(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        return baseDAO.findByEmail(User.class, email);
    }

    @Override
    public User verifyUser(String email, String name) {
        User user = baseDAO.findByEmail(User.class, email);
        if (user == null) {
            baseDAO.save(new User(name, email, BASE_LEVEL));
        }

        return baseDAO.findByEmail(User.class, email);
    }
}
