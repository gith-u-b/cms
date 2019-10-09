package com.aaa.yf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.aaa.yf.cache.CountClickCache;

/**
 * Servlet Filter implementation class ClickCountFilter
 */
public class ClickCountFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ClickCountFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURI();
			String strs[] = url.split("/");
			String str = strs[strs.length - 1];
			
			if(str.indexOf("_") != -1 && str.indexOf(".action") != -1){  //如果是访问的动态新闻，截取新闻id
				String id = request.getParameter("content.contentId");  
				CountClickCache.add(id);
			}else{
				if(str.contains("news")){       //访问的静态新闻截取id
					String id=str.substring(4,str.length()-5);
					CountClickCache.add(id);
				}
			}
			
			CountClickCache.output();
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
