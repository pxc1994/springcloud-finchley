package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    interface FilterType{
      //路由之前
      String  PRE = "pre";
      //路由之时
      String ROUTING = "routing";
      //路由之后
      String POST = "post";
      //错误调用
      String ERROR = "error";
    }


    @Override
    public String filterType() {
        return FilterType.PRE;
    }

    @Override
    public int filterOrder() {
        //过滤顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //写逻辑判断是否过滤
        return true;
    }


    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(499);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){

            }
            return null;
        }
        log.info("ok");
        return null;
    }
}
