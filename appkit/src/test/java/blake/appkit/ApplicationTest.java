package blake.appkit;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.application.Application;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.responder.DefaultResponder;
import blake.appkit.application.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class ApplicationTest {

    protected Application application;
    protected Configuration settings;
    protected List<Location> pages;

    @Before
    public void before() {
        this.pages = new ArrayList<Location>();
        Configuration config = new Configuration(null) {

            @Override
            public List<Location> getLocations() {
                return ApplicationTest.this.pages;
            }
        };
        application = new Application(config);
    }

    @Test
    public void testEmptyApplication() {
        Request request = new Request("/schnick/");
        Response response = application.respond(request);
        assertNotNull(response);
        assertEquals(StatusCode.HTTP_200, response.getStatus());
        assertTrue(response.getContent().isEmpty() == false);
    }

    @Test
    public void testLocationResolving() {
        pages.add(new Location("^schnick/(\\d+)/", TestPage.class, new Context()));
        Request request = new Request("/schnick/" + System.currentTimeMillis() + "/");

        Response response = application.respond(request);
        assertNotNull(response);
        assertEquals(StatusCode.HTTP_200, response.getStatus());
        assertTrue(response.getContent().isEmpty() == false);
    }

    @Test
    public void testFiveLocations() {
        pages.add(new Location("^/schnick/", TestPage.class, new Context()));
        pages.addAll(Arrays.asList(
                new Location("^schnick/$", TestPage.class, new Context()),
                new Location("^schnack/$", TestPage.class, new Context()),
                new Location("^schnuck/$", TestPage.class, new Context()),
                new Location("^bling/blang/$", TestPage.class, new Context()),
                new Location("^schnick/schnack/(\\d+)/$", TestPage.class, new Context())));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Request request = new Request("/schnick/schnack/" + System.currentTimeMillis() + "/");
            Response response = application.respond(request);
            assertNotNull(response);
            assertEquals(StatusCode.HTTP_200, response.getStatus());
            assertTrue(response.getContent().isEmpty() == false);
            assertTrue(response.getContent().length() > 5);
        }
        System.out.println("five items:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void testTenLocations() {
        pages.add(new Location("^/schnick/", TestPage.class, new Context()));
        pages.addAll(Arrays.asList(
                new Location("^schnick/$", TestPage.class, new Context() {{
                    put("template", "schnack");
                }}),
                new Location("^schnack/$", TestPage.class, new Context()),
                new Location("^schnuck/$", TestPage.class, new Context()),
                new Location("^bling/blang/$", TestPage.class, new Context()),
                new Location("^bling/blong/$", DefaultResponder.class, new Context()),
                new Location("^ring/$", TestPage.class, new Context()),
                new Location("^blub/$", TestPage.class, new Context()),
                new Location("^tada/$", TestPage.class, new Context()),
                new Location("^bloing/blang/schnick/$", TestPage.class, new Context()),
                new Location("^schnick/schnack/(\\d+)/$", DefaultResponder.class, new Context())));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Request request = new Request("/schnick/schnack/" + System.currentTimeMillis() + "/");
            Response response = application.respond(request);
            assertNotNull(response);
            assertEquals(StatusCode.HTTP_200, response.getStatus());
            assertTrue(response.getContent().isEmpty() == false);
        }
        System.out.println("ten items:" + (System.currentTimeMillis() - start));
    }
}
