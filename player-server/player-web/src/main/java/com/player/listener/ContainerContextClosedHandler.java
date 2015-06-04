package com.player.listener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author Mykola_Zalyayev
 */
@WebListener // register it as you wish
public class ContainerContextClosedHandler implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerContextClosedHandler.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // nothing to do
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        Driver driver = null;

        // clear drivers
        while (drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            } catch (SQLException ex) {
                LOGGER.error("Driver deregistration failed",ex);
            }
        }

        // MySQL driver leaves around a thread. This static method cleans it up.
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            LOGGER.error("Driver deregistration failed",e);
        }
    }

}
