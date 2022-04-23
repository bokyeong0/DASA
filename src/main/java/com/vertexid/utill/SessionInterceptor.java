package com.vertexid.utill;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.vo.SessionVo;


public class SessionInterceptor implements HandlerInterceptor{
	
	@Autowired
	HttpSession session;
	
	Logger logger = Logger.getLogger("dasa");
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("\n");
		String strURL = request.getRequestURI();
		logger.debug(strURL + " >>>");

		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String[] val = request.getParameterValues(name);
			String str = "name: [" + name + "] ";
			for (String s : val) {
				str += "value: [" + s + "] ";
			}
			logger.debug(str);
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		String className = handler.getClass().getName();

		className = className.replace('.', '\\');
		String jspName = "";
		if(modelAndView != null)
			jspName = modelAndView.getViewName();
		else
			jspName = null;
		if (jspName == null) {
			jspName = "unknown jsp file";

		}
		jspName = jspName.replace("/", "\\");
//		logger.debug("CLASS NAME: " + javaRootPath + className + ".java");
//		logger.debug("VIEW NAME: " + jspRootPath + jspName + ".jsp");
		logger.debug("CLASS NAME: " + className + ".java");
		logger.debug("VIEW NAME: " + jspName + ".jsp");
		logger.debug(request.getRequestURI() + " <<<");
		

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod)handler;
//			if(method.getBeanType().getCanonicalName().toString().equals("com.seoulmilk.asset.controller.MobileController")){
//			}else{
				SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");
				if (sessionVo == null && modelAndView != null) {
//					response.setStatus(HttpServletResponse.SC_ACCEPTED, "세션 종료!!");
					System.out.println("인터셉트");
					modelAndView.setViewName("redirect:/");
//				}else if(sessionVo != null){
//					sessionVo.setSystem_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}else if (sessionVo == null && modelAndView == null) {
//					response.setStatus(HttpServletResponse.SC_ACCEPTED);
					modelAndView =  new  ModelAndView();
					modelAndView.setViewName("redirect:/");
				}
//			}
		}
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
