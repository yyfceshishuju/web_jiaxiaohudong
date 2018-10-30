package com.jiaxiaohudong.interceptor;

import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.entity.Userinfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lcf12307 on 2018/10/27.
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        CommonUser user = (CommonUser) session.getAttribute("userinfo");
        if (null == user) {

            response.sendRedirect("/login.do");
            return false;
        }
        return  true;
    }

}