import eu.dreamix.ServerVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;
import org.junit.*;
import org.junit.runner.RunWith;

/**
 * @author Boyko Dimitrov on 6/26/17.
 */
@RunWith(VertxUnitRunner.class)
public class CurrencyServiceTest {

    private static Vertx vertx;
    private static WebClient webClient;

    @Before
    public void setUp() throws Exception {
        vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());
        webClient = WebClient.create(vertx);
    }

    @Test
    public void getCurrency(TestContext context) {
        webClient.getAbs("http://localhost:8088/currency").send(context.asyncAssertSuccess((gr) -> {
            context.assertEquals(true, gr.bodyAsJsonObject().containsKey("base"));
            context.assertEquals(true, gr.bodyAsJsonObject().containsKey("date"));
            context.assertEquals(true, gr.bodyAsJsonObject().containsKey("rates"));
        }));
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }
}
