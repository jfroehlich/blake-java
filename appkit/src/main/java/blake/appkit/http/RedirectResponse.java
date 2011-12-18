package blake.appkit.http;

public class RedirectResponse extends Response {
    
    public RedirectResponse(String url) {
        super(StatusCode.HTTP_302, "");
        put("Location", url);
    }
}
