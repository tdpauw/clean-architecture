package io.thinkinglabs.client_files.client;

import io.thinkinglabs.client_files.UUIDGenerator;
import org.webbitserver.*;

import javax.persistence.EntityManagerFactory;

/**
 * @author @tdpauw
 */
public class ClientHandler implements HttpHandler
{
    private final EntityManagerFactory emf;

    public ClientHandler(final EntityManagerFactory emf)
    {

        this.emf = emf;
    }

    @Override
    public void handleHttpRequest(final HttpRequest request, final HttpResponse response, final HttpControl control) throws Exception
    {
        if ("POST".equals(request.method()))
        {
            final DefaultCreateClient createClient = new DefaultCreateClient(new PersistedClientBase(emf.createEntityManager(), new ToPersistedClientMapper()), new UUIDGenerator());
            createClient.createClient(new CreateClientCommand("firstname", "lastname"));
        }
        else
        {
            control.nextHandler();
        }
    }
}
