package io.thinkinglabs.clean_architecture;

import io.thinkinglabs.clean_architecture.client.ClientHandler;
import org.webbitserver.WebServer;
import org.webbitserver.netty.NettyWebServer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Thierry de Pauw
 * @since 24/01/2017
 */
public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clean_architecture");
        WebServer webServer = new NettyWebServer(8080);
        webServer.add("/api/client", new ClientHandler(emf));
    }
}
