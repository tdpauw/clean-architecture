package io.thinkinglabs.clean_architecture;

import io.thinkinglabs.clean_architecture.client.Client;
import io.thinkinglabs.clean_architecture.client.CreateClient;
import io.thinkinglabs.clean_architecture.client.CreateClientCommand;
import io.thinkinglabs.clean_architecture.client.DefaultCreateClient;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author @tdpauw
 */
public class ServerVerticle extends AbstractVerticle
{
    private final CreateClient createClient;

    public ServerVerticle(final DefaultCreateClient createClient)
    {
        this.createClient = createClient;
    }

    @Override
    public void start(final Future<Void> future) throws Exception
    {
        Router router = Router.router(vertx);
        router.get("/").handler(this::home);
        router.route("/api/clients/*").handler(BodyHandler.create());
        router.post("/api/clients").blockingHandler(this::createClient);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, result ->
                {
                    if (result.succeeded())
                    {
                        future.complete();
                    }
                    else
                    {
                        future.fail(result.cause());
                    }
                });
    }

    private void createClient(final RoutingContext routingContext)
    {
        final JsonObject bodyAsJson = routingContext.getBodyAsJson();
        Client client = createClient.createClient(new CreateClientCommand(bodyAsJson.getString("firstname"), bodyAsJson.getString("lastname")));
        routingContext.response()
                .setStatusCode(201)
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .putHeader(HttpHeaders.LOCATION, computeLocationHeader(routingContext.request(), client.getClientId()))
                .end(new JsonObject().put("firstname", client.getFirstname()).put("lastname", client.getLastname()).encode());
    }

    private String computeLocationHeader(final HttpServerRequest request, final String id)
    {
        return request.absoluteURI() + "/" + id;
    }

    private void home(RoutingContext routingContext)
    {
        routingContext.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "text/html")
                .end("<h1>It's working</h1>");
    }
}
