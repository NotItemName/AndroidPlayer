package h2.web.console;

import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * @author Николай
 */
public class WebConsole {

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
}
