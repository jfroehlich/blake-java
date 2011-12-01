package blake.appkit.http;

import java.io.Serializable;

public class Request implements Serializable {

    private final String path;

    public Request(String path) {
        this.path = path;
    }

    public String getPath() {
        return new String(path);
    }
}
