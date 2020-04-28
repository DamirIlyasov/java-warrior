package ru.itis.javawarrior.db.dao;

import ru.itis.javawarrior.db.model.BaseEntity;

import java.util.List;

public interface BaseDAO {

    <T extends BaseEntity> T findById(Class<T> clazz, Long id);

    <T extends BaseEntity> T findByEmail(Class<T> clazz, String email);

    Long getCount(Class<?> clazz);

    void save(BaseEntity o);

    <T extends BaseEntity> void delete(Class<T> clazz, Long id);

    <T> List getList(Class<T> clazz);
}
