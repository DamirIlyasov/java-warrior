package ru.itis.javawarrior.db.service;

import ru.itis.javawarrior.db.model.AppUser;

public interface UserService {

    void save(AppUser user);

    void delete(Long id);

    AppUser findById(Long id);

    AppUser findByEmail(String email);

    AppUser getCurrentUser();
}
