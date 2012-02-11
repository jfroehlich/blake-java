package blake.appkit;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.responder.Responder;

public class TestPage extends Responder {
    
    public TestPage(Configuration settings) {
        super(settings);
    }

    @Override
    public Response process(Request request, Context ctx) {
        ctx.put("timestamp", "" + System.currentTimeMillis());
        ctx.put("template", "TIME: {{timestamp}}");
        String str = settings.getRenderer().render(ctx);
        return new Response(str);
    }
}
