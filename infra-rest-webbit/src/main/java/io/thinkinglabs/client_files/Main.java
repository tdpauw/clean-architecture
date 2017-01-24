package io.thinkinglabs.client_files;

import io.thinkinglabs.client_files.client.ClientHandler;
import io.thinkinglabs.client_files.client.DefaultCreateClient;
import io.thinkinglabs.client_files.client.PersistedClientBase;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("client_files");
        WebServer webServer = new NettyWebServer(8080);
        webServer.add("/api/client", new ClientHandler(emf));
    }
}
