package com.jumpfish.developer.porjects.monitors.access.request;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class SecurityContextUtils {

    public static final String KEY_NAME_USERNAME = "username";
    public static final String KEY_NAME_TENANTID = "tenant_id";
    public static final String KEY_NAME_USERID = "user_id";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String PAGE_NO = "page_no";
    public static final String PAGE_SIZE = "page_size";
    public static final String REQUEST_TIME = "request_time";
    public static final String REQUEST_ADJUST_TIME = "request_adjust_time";

    public static String getRequestTime() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(REQUEST_TIME);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {
            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(REQUEST_TIME)).orElse(null);
        }

        return null;
    }

    public static String getRequestAdjustTime() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(REQUEST_ADJUST_TIME);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {
            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(REQUEST_ADJUST_TIME)).orElse(null);
        }

        return null;
    }

    public static String getPageNo() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(PAGE_NO);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {

            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(PAGE_NO)).orElse(null);
        }

        return null;
    }

    public static String getPageSize() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(PAGE_SIZE);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {

            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(PAGE_SIZE)).orElse(null);
        }

        return null;
    }

    public static String getCurrentUserToken() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(ACCESS_TOKEN);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {

            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(ACCESS_TOKEN)).orElse(null);
        }

        return null;
    }

    public static String getCurrentUserId() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(KEY_NAME_USERID);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {

            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(ACCESS_TOKEN)).orElse(null);
        }

        return null;
    }

    public static String getCurrentTenantId() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(KEY_NAME_TENANTID);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {

            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(KEY_NAME_TENANTID)).orElse(null);
        }

        return null;
    }

    public static String getCurrentUsername() {
        HttpServletRequest request = getRequest();
        String encodeName = request.getHeader(KEY_NAME_USERNAME);
        if (encodeName != null) {
            try {
                return URLDecoder.decode(encodeName, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ignored) {

            }
        } else {
            return (String) Optional.ofNullable(request.getSession(false)).map(session -> session.getAttribute(KEY_NAME_USERNAME)).orElse(null);
        }

        return null;
    }

    /**
     * 获取当前线程的request对象
     *
     * @return HttpServletRequest
     * @throws IllegalStateException 在非web线程中调用该方法
     */
    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new IllegalStateException("当前线程中不存在Request上下文");
        }
        return requestAttributes.getRequest();
    }
}
