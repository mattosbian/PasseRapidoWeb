package br.com.passerapido.webfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.passerapido.util.EntityManagerUtil;

/**
 * Servlet Filter implementation class SessionViewFilter
 */
@WebFilter("/*")
public class SessionViewFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionViewFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			EntityManagerUtil.createEntityManager(request.getServletContext());
			
			chain.doFilter(request, response);			
			
		} catch (ServletException e) {
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
