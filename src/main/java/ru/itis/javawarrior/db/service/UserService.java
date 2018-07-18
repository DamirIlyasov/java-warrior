package ru.itis.javawarrior.db.service;

import ru.itis.javawarrior.db.model.User;

public interface UserService {

    void save(User user);

    void delete(Long id);

    User findById(Long id);

    User findByEmail(String email);

    User verifyUser(String email, String name);
}
