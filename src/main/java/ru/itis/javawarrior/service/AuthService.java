package ru.itis.javawarrior.service;

import ru.itis.javawarrior.model.User;

public interface AuthService {

    void save(User user);

    void delete(Long id);

    User findById(Long id);
}
