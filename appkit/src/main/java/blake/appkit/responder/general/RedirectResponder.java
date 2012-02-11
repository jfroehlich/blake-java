package blake.appkit.responder.general;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.PermanentRedirectResponse;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.responder.Responder;

public class RedirectResponder extends Responder {
    public static final String REDIRECT_PATH = "redirect_path";
    
    public RedirectResponder(Configuration settings) {
        super(settings);
    }
    
    public Response respond(Request request, Context context) throws Exception {
        if (!context.containsKey(REDIRECT_PATH)) {
            throw new Exception("No field '" + REDIRECT_PATH + "' set in the context.");
        }
        String path = context.get(REDIRECT_PATH);
        return new PermanentRedirectResponse(path);
    }

}
