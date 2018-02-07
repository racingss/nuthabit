package com.nuthabit.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nuthabit.dao.Kehu;
import com.nuthabit.util.KehuUtil;

@WebFilter(urlPatterns = {"/myplan/index.html"})
public class EnviromentFilter implements Filter {
	Log log=LogFactory.getLog(this.getClass().getName());

	private static Properties envProp = new Properties();
	private static final String KEY_DEV_ENV = "DEV.ENV";
	private static final String KEY_DEV_ENV_KEHUID = "DEV.ENV.KEHU.ID";
	private static final String ENABLED_DEV_ENV = "ON";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (envProp.isEmpty()) {
			 try {
				InputStream propStream = getClass().getResourceAsStream("/env.properties");
				envProp.load(propStream);
				propStream.close();		
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				envProp = null;
			} catch (IOException e) {
				e.printStackTrace();
				envProp = null;
			} catch (Exception e) {
				e.printStackTrace();
				envProp = null;
			}
		}		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String env = envProp.getProperty(KEY_DEV_ENV);
		if (ENABLED_DEV_ENV.equalsIgnoreCase(env)) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpSession session = httpRequest.getSession();
			if (session.getAttribute("kehu") == null) {
				mockUser(httpRequest, response);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	private void mockUser(ServletRequest request, ServletResponse response) {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpSession session = httpRequest.getSession();
			Kehu kehu = new KehuUtil().getKehuById(envProp.getProperty(KEY_DEV_ENV_KEHUID));
			session.setAttribute("kehu", kehu);
			log.debug("set DEV ENV on , mock user Kehu id " + kehu.getId());
		} catch (Exception e) {
			log.error(e);
		}
	}
}
