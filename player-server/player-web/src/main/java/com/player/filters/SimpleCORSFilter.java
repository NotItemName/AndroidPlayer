package com.player.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mykola_Zalyayev
 */
public class SimpleCORSFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SimpleCORSFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
        chain.doFilter(req, res);
    }
    
    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.debug("Init");
    }
    
    @Override
    public void destroy() {
        LOGGER.debug("Destroy");
    }

}
