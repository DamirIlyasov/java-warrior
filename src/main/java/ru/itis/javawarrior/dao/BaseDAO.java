package ru.itis.javawarrior.dao;

import ru.itis.javawarrior.entity.BaseEntity;

import java.util.List;

public interface BaseDAO {

    <T extends BaseEntity> T findById(Class<T> clazz, Long id);

    Long getCount(Class<?> clazz);

    Long save(BaseEntity o);

    <T extends BaseEntity> T delete(Class<T> clazz, Long id);

    <T> List getList(Class<T> clazz);
}
