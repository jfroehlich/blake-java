package blake.appkit;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.responder.Responder;

public class TestPage extends Responder {
    
    private static final String TEMPLATE = "timestamp: {{ timestamp }}";
    
    public TestPage(Configuration settings) {
        super(settings);
    }

    @Override
    public Response respond(Request request, Context ctx) {
        String template = 
        ctx.put("timestamp", "" + System.currentTimeMillis());
        String str = settings.getRenderer().render(TEMPLATE, ctx);
        return new Response(str);
    }
}
