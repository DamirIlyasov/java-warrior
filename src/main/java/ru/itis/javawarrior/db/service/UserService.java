package ru.itis.javawarrior.db.service;

import ru.itis.javawarrior.db.model.AppUser;

public interface UserService {

    void save(AppUser user);

    AppUser findByEmail(String email);

    AppUser getCurrentUser();
}
