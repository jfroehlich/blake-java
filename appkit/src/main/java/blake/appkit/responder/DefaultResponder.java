package blake.appkit.responder;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;

/**
 * Default welcome page.
 * 
 * This page is used when the page is a fresh application and the location store
 * is empty. So there is no need to optimize this page for speed.
 *
 */
public class DefaultResponder extends Responder {
    
    public DefaultResponder(Configuration settings) {
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
