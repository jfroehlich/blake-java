package blake.appkit.pages;

import blake.appkit.Configuration;
import blake.appkit.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;

public class SimplePage extends Page {
    
    public SimplePage(Configuration settings) {
        super(settings);
    }

    @Override
    public Response process(Request request, Context context) {
        String str = settings.getRenderer().render(context);
        return new Response(str);
    }
}
