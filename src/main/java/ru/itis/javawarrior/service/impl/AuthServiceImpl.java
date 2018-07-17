package ru.itis.javawarrior.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javawarrior.dao.BaseDAO;
import ru.itis.javawarrior.model.User;
import ru.itis.javawarrior.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

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
}
