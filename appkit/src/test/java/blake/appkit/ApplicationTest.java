package blake.appkit;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.application.Application;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.pages.DefaultPage;
import blake.appkit.pages.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class ApplicationTest {

    protected Application application;
    protected Configuration settings;
    protected List<Path> pages;

    @Before
    public void before() {
        this.pages = new ArrayList<Path>();
        Configuration config = new Configuration(null) {

            @Override
            public List<Path> getPages() {
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
        pages.add(new Path("^schnick/(\\d+)/", TestPage.class, new Context()));
        Request request = new Request("/schnick/" + System.currentTimeMillis() + "/");

        Response response = application.respond(request);
        assertNotNull(response);
        assertEquals(StatusCode.HTTP_200, response.getStatus());
        assertTrue(response.getContent().isEmpty() == false);
    }

    @Test
    public void testFiveLocations() {
        pages.add(new Path("^/schnick/", TestPage.class, new Context()));
        pages.addAll(Arrays.asList(
                new Path("^schnick/$", TestPage.class, new Context()),
                new Path("^schnack/$", TestPage.class, new Context()),
                new Path("^schnuck/$", TestPage.class, new Context()),
                new Path("^bling/blang/$", TestPage.class, new Context()),
                new Path("^schnick/schnack/(\\d+)/$", TestPage.class, new Context())));

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
        pages.add(new Path("^/schnick/", TestPage.class, new Context()));
        pages.addAll(Arrays.asList(
                new Path("^schnick/$", TestPage.class, new Context() {{
                    put("template", "schnack");
                }}),
                new Path("^schnack/$", TestPage.class, new Context()),
                new Path("^schnuck/$", TestPage.class, new Context()),
                new Path("^bling/blang/$", TestPage.class, new Context()),
                new Path("^bling/blong/$", DefaultPage.class, new Context()),
                new Path("^ring/$", TestPage.class, new Context()),
                new Path("^blub/$", TestPage.class, new Context()),
                new Path("^tada/$", TestPage.class, new Context()),
                new Path("^bloing/blang/schnick/$", TestPage.class, new Context()),
                new Path("^schnick/schnack/(\\d+)/$", DefaultPage.class, new Context())));

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
