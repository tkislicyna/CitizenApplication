package ru.nsk.tkozlova.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.nsk.tkozlova.controllers.model.SearchModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @project CitizenApplication
 * @autor Toma on 5/8/2018.
 */
public class CitizenSearchInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(
            HttpServletRequest req,
            HttpServletResponse res,
            Object o,
            ModelAndView model) throws Exception {

        if (model != null) {
            model.addObject("searchForm", new SearchModel());
        }
    }
}
