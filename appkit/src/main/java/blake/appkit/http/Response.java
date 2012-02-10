package blake.appkit.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author jfroehlich
 */
public class Response implements Serializable {
    
    protected final Map<String, String> headers;
    protected final StatusCode status;
    protected String mimeType = "text/html";
    protected String content;
        
    public Response(StatusCode status, String content) {
        this.headers = new HashMap<String, String>();
        this.status = status;
        this.content = content;
    }

    public Response(String content) {
        this(StatusCode.HTTP_200, content);
    }

    public StatusCode getStatus() {
        return status;
    }

    public String getMimeType() {
        return mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Map<String, String> getHeaders() {
        return headers;
    }
    
    public void put(String header, String value) {
        headers.put(header, value);
    }
    
    public void putAll(Map<String, String>headers) {
        headers.putAll(headers);
    }
    
    public String remove(String header) {
        return headers.remove(header);
    }
}
