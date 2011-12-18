package blake.appkit.http;

public class PermanentRedirectResponse extends Response {

    public PermanentRedirectResponse(String url) {
        super(StatusCode.HTTP_301, "");
        put("Location", url);
    }
}
