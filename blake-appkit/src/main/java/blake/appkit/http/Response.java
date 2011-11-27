package blake.appkit.http;

import java.io.Serializable;

public class Response implements Serializable {

    protected final StatusCode status;
    protected String body;

    public Response(StatusCode status, String body) {
        this.status = status;
        this.body = body;
    }

    public Response(String body) {
        this(StatusCode.HTTP_200, body);
    }

    public StatusCode getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
