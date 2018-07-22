package ru.itis.javawarrior.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.javawarrior.db.dao.BaseDAO;
import ru.itis.javawarrior.db.model.AppUser;
import ru.itis.javawarrior.db.service.UserService;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Long BASE_LEVEL = 1L;

    @Autowired
    private BaseDAO baseDAO;

    @Override
    public void save(AppUser user) {
        baseDAO.save(user);
    }

    @Override
    public void delete(Long id) {
        baseDAO.delete(AppUser.class, id);
    }

    @Override
    public AppUser findById(Long id) {
        return baseDAO.findById(AppUser.class, id);
    }

    @Override
    public AppUser findByEmail(String email) {
        return baseDAO.findByEmail(AppUser.class, email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = findByEmail(s);
        return new User(user.getEmail(), user.getPassword(), emptyList());
    }

    @Override
    public AppUser getCurrentUser(){
        return findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
