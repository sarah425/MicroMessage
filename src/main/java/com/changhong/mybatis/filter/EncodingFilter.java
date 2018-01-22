package com.changhong.mybatis.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

	String encodingType = null;
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        System.out.println("====EncodingFilter Constructor====");
    }


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encodingType = fConfig.getInitParameter("encoding");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (!encodingType.equals(request.getCharacterEncoding())) {
			request.setCharacterEncoding(encodingType);
		}
		response.setCharacterEncoding(encodingType);
		chain.doFilter(request, response);
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("====EncodingFilter Destroy====");
	}



}
