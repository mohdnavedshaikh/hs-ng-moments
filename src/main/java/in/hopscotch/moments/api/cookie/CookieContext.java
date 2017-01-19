package in.hopscotch.moments.api.cookie;

import java.util.HashMap;
import java.util.Map;

public class CookieContext {

    final Map<String, String> context = new HashMap<>();

    public String getCookie(String key) {
        return context.get(key);
    }

    public void addCookie(String key, String value) {
        context.put(key, value);
    }

}
