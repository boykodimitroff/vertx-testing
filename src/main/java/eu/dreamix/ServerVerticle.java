package eu.dreamix;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.sync.SyncVerticle;
import io.vertx.ext.web.Router;

import static io.vertx.ext.sync.Sync.fiberHandler;

/**
 * @author Boyko Dimitrov on 6/26/17.
 */
public class ServerVerticle extends SyncVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router( vertx );

        server.requestHandler(router::accept).listen(8088);

        initRoutes(router);
    }

    private void initRoutes(Router router) {
        router.route(HttpMethod.GET, "/currency").handler(fiberHandler(new CurrencyService()::getCurrency));
    }
}
