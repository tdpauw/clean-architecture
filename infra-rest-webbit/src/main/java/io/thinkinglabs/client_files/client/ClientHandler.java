package io.thinkinglabs.client_files.client;

import org.webbitserver.*;

import javax.persistence.EntityManagerFactory;

/**
 * @author Thierry de Pauw
 * @since 24/01/2017
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
            final DefaultCreateClient createClient = new DefaultCreateClient(new PersistedClientBase(emf.createEntityManager(), new ToPersistedClientMapper()));
            createClient.createClient(new CreateClientCommand("firstname", "lastname"));
        }
        else
        {
            control.nextHandler();
        }
    }
}
