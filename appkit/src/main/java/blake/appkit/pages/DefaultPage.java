package blake.appkit.pages;

import blake.appkit.Configuration;
import blake.appkit.Context;
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
    
    public DefaultPage(Configuration settings) {
        super(settings);
    }
    
    private static final String TEMPLATE = "<!DOCTYPE html><html><head>"
            + "<meta charset=\"utf-8\" /><title>Welcome</title>"
            + "</head><body><h1>Welcome to the show.</h1></body>"
            + "</html>";

    @Override
    public Response process(Request request, Context context) {
        return new Response(TEMPLATE);
    }
}
