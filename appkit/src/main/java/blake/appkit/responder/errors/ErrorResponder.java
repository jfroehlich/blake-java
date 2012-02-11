package blake.appkit.responder.errors;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.responder.Responder;

/**
 * Default resource for broken responses.
 * 
 * This is the default resource displayed when an uncaught exception is 
 * passed to the application from other resources. It responds with a HTTP 500
 * status (Internal server error) and a optional custom message.
 * 
 * @author jfroehlich
 */
public class ErrorResponder extends Responder {

    public static final String ERROR_MESSAGE_KEY = "error_message";
    
    public ErrorResponder(Configuration settings) {
        super(settings);
    }
    
    @Override
    public Response respond(Request request, Context context) {
        StringBuilder message = new StringBuilder("Internal server error");
        message.append("\n\n");
        if (context.containsKey(ERROR_MESSAGE_KEY)) {
            String msg = context.get(ERROR_MESSAGE_KEY);
            message.append(msg);
        } else {
            message.append("An error occured on the server.\n");
            message.append("The page you requested could not be served.");
        }
        return new Response(StatusCode.HTTP_500, message.toString());
    }
}
