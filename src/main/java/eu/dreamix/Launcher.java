package eu.dreamix;

import io.vertx.core.Vertx;

/**
 * @author Boyko Dimitrov on 6/26/17.
 */
public class Launcher {

    public static void main(String args[]) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());
    }
}
