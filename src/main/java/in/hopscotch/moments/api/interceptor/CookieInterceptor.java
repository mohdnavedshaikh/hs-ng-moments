package in.hopscotch.moments.api.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.hopscotch.moments.api.cookie.CookieContext;

public class CookieInterceptor extends HandlerInterceptorAdapter {

    private CookieContext cookieContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookieContext.addCookie(cookie.getName(), cookie.getValue());
            }
        return true;
    }

    @Inject
    public void setCookieContext(CookieContext cookieContext) {
        this.cookieContext = cookieContext;
    }

}
