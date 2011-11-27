package blake.appkit.pages.errors;

import blake.appkit.http.Request;
import blake.appkit.http.Response;
import blake.appkit.http.StatusCode;
import blake.appkit.pages.Page;

public class ErrorPage extends Page {
    
    @Override
    public Response render(Request request) {
        return new Response(StatusCode.HTTP_500, "Internal server error");
    }
}
