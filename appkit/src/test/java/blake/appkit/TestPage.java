package blake.appkit;

import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.pages.Page;

public class TestPage extends Page {
    
    public TestPage(Configuration settings) {
        super(settings);
    }

    @Override
    public Response process(Request request, Context ctx) {
        ctx.put("timestamp", "" + System.currentTimeMillis());
        ctx.put("template", "TIME: {{timestamp}}");
        String str = settings.getRenderer().render(ctx);
        return new Response(str);
        //return new Response("timestamp:" + System.currentTimeMillis());
    }
}
