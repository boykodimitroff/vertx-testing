package eu.dreamix;

import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;

import static io.vertx.ext.sync.Sync.awaitResult;

/**
 * @author Boyko Dimitrov on 6/26/17.
 */
public class CurrencyService {

    @Suspendable
    public void getCurrency(RoutingContext routingContext) {
        awaitResult(h -> {
            WebClient client = WebClient.create(Vertx.currentContext().owner());
            client.get(80, "api.fixer.io", "/latest").send(ar -> {
                routingContext.response().end(ar.result().bodyAsJsonObject().toString());
            });
        });
    }
}
