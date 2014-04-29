package com.player.controller;

import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author Mykola_Zalyayev
 */
@Controller
public class Test {

    @Autowired
    private SessionFactory factory;

    public static void main(String[] args) {
        Server server;
        try {
            server = Server.createWebServer("-web", "-webPort", "8090");
            server.start();
            System.out.println("               Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String web() {
        User user = new User();
        user.setId(1);
        factory.getCurrentSession().save(user);

        User user1 = (User) factory.getCurrentSession().load(User.class, 1);

        return user1.getId().toString();
    }
}
