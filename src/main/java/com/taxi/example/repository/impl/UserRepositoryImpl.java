package com.taxi.example.repository.impl;

import com.taxi.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


/*
    Сервис с Hibernate
*/
@Repository
@AllArgsConstructor
@Slf4j
public class UserRepositoryImpl {
    private SessionFactory sessionFactory;


    public void saveUser(User user) {
        sessionFactory.openSession().save(user);
    }

    public User getByUserName(String login) {
        return sessionFactory.openSession().createNativeQuery(
                String.format("SELECT user_id, login, password, role FROM public.users WHERE login = %s", login), User.class
        ).list().get(0);
    }

    public User getById(Long id) {
        User user = sessionFactory.openSession().get(User.class, id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }
}
