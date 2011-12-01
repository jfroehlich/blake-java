package blake.appkit;

import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.pages.Page;

public class TestPage extends Page {

    @Override
    public Response render(Request request) {
        return new Response("timestamp:" + System.currentTimeMillis());
    }
}
