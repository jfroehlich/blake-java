package blake.appkit.http;

public enum StatusCode {
    HTTP_200(200, "OK"),
    HTTP_301(301, "Moved Permanently"),
    HTTP_404(404, "Not Found"),
    HTTP_410(410, "Gone"),
    HTTP_500(500, "Internal Server Error"),
    HTTP_501(501, "Not Implemented");
    
    private final int code;
    private final String message;

    private StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
