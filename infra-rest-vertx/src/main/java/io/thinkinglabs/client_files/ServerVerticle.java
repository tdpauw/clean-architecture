package io.thinkinglabs.client_files;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * @author thipau
 * @since 30/01/2017
 */
public class ServerVerticle extends AbstractVerticle
{
    @Override
    public void start(final Future<Void> future) throws Exception
    {
        Router router = Router.router(vertx);
        router.get("/").handler(this::hello);

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

    private void hello(RoutingContext routingContext)
    {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/html")
                .end("<h1>Hello from my first " +
                        "Vert.x 3 application</h1>");
    }
}
