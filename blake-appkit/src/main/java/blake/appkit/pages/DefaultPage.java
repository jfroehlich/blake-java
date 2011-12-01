package blake.appkit.pages;

import blake.appkit.http.Request;
import blake.appkit.http.Response;

/**
 * Default welcome page.
 * 
 * This page is used when the page is a fresh application and the location store
 * is empty. So there is no need to optimize this page for speed.
 *
 */
public class DefaultPage extends Page {

    private static final String TEMPLATE = "<!DOCTYPE html><html><head>"
            + "<meta charset=\"utf-8\" /><title>Welcome</title>"
            + "</head><body><h1>Welcome to the show.</h1></body>"
            + "</html>";

    @Override
    public Response render(Request request) {
        return new Response(TEMPLATE);
    }
}
