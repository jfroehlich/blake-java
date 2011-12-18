package blake.appkit.pages;

import blake.appkit.application.Configuration;
import blake.appkit.application.Context;
import blake.appkit.http.Request;
import blake.appkit.http.Response;
import java.io.Serializable;

public abstract class Page implements Serializable {
    
    protected final Configuration settings;
    
    public Page(Configuration settings) {
        this.settings = settings;
    }
    
    public abstract Response process(Request request, Context context);
}
