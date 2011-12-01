package blake.appkit.pages;

import blake.appkit.http.Request;
import blake.appkit.http.Response;
import java.io.Serializable;

public abstract class Page implements Serializable {
    public abstract Response render(Request request);
}
