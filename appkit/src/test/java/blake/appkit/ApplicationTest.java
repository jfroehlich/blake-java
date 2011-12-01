package blake.appkit;

import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.pages.DefaultPage;
import blake.appkit.pages.PageResolver;
import blake.appkit.pages.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class ApplicationTest {

    protected Application application;
    protected Configuration settings;

    @Before
    public void before() {
        Map<String, String> context = new HashMap<String, String>();
        Configuration settings = new Configuration(context) {

            private static final long serialVersionUID = 1L;

            @Override
            public List<List<Object>> getPages() {
                return new ArrayList<List<Object>>();
            }
        };
        application = new Application(settings);
    }

    @Test
    public void testEmptyApplication() {
        Request request = new Request("/schnick/");
        Response response = application.respond(request);
        assertNotNull(response);
        assertEquals(StatusCode.HTTP_200, response.getStatus());
        assertTrue(response.getBody().isEmpty() == false);
    }

    @Test
    public void testLocationResolving() {
        PageResolver pages = application.getPageResolver();
        pages.add(new Path("^schnick/(\\d+)/", TestPage.class));
        Request request = new Request("/schnick/" + System.currentTimeMillis() + "/");

        Response response = application.respond(request);
        assertNotNull(response);
        assertEquals(StatusCode.HTTP_200, response.getStatus());
        assertTrue(response.getBody().isEmpty() == false);
    }

    @Test
    public void testFiveLocations() {
        PageResolver pages = application.getPageResolver();
        pages.add(new Path("^/schnick/", TestPage.class));
        pages.addAll(Arrays.asList(
                new Path("^schnick/$", TestPage.class),
                new Path("^schnack/$", TestPage.class),
                new Path("^schnuck/$", TestPage.class),
                new Path("^bling/blang/$", TestPage.class),
                new Path("^schnick/schnack/(\\d+)/$", TestPage.class)));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Request request = new Request("/schnick/schnack/" + System.currentTimeMillis() + "/");
            Response response = application.respond(request);
            assertNotNull(response);
            assertEquals(StatusCode.HTTP_200, response.getStatus());
            assertTrue(response.getBody().isEmpty() == false);
            assertTrue(response.getBody().length() > 5);
        }
        System.out.println("five items:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void testTenLocations() {
        PageResolver pages = application.getPageResolver();
        pages.add(new Path("^/schnick/", TestPage.class));
        pages.addAll(Arrays.asList(
                new Path("^schnick/$", TestPage.class),
                new Path("^schnack/$", TestPage.class),
                new Path("^schnuck/$", TestPage.class),
                new Path("^bling/blang/$", TestPage.class),
                new Path("^bling/blong/$", DefaultPage.class),
                new Path("^ring/$", TestPage.class),
                new Path("^blub/$", TestPage.class),
                new Path("^tada/$", TestPage.class),
                new Path("^bloing/blang/schnick/$", TestPage.class),
                new Path("^schnick/schnack/(\\d+)/$", DefaultPage.class)));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Request request = new Request("/schnick/schnack/" + System.currentTimeMillis() + "/");
            Response response = application.respond(request);
            assertNotNull(response);
            assertEquals(StatusCode.HTTP_200, response.getStatus());
            assertTrue(response.getBody().isEmpty() == false);
        }
        System.out.println("ten items:" + (System.currentTimeMillis() - start));
    }
}
