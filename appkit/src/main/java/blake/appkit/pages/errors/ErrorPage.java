package blake.appkit.pages.errors;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.pages.Page;

public class ErrorPage extends Page {

    public static final String ERROR_MESSAGE_KEY = "error_message";
    
    public ErrorPage(Configuration settings) {
        super(settings);
    }
    
    @Override
    public Response process(Request request, Context context) {
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
