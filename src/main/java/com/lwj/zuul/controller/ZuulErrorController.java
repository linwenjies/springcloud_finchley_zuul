package com.lwj.zuul.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
public class ZuulErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@Autowired
	private ErrorAttributes errorAttributes;
	
	/**
	 * 这个方法写自己具体业务的逻辑，返回值等按照约定的来
	 * 增加这个方法的原因是，以往的ControllerAdvice并不能处理zuul层抛出的异常
	 * 
	 * @return
	 */
	@RequestMapping("/error")
	public String error(HttpServletRequest request) {
		Map<String, Object> errorAttributes = getErrorAttributes(request);
		String message = (String) errorAttributes.get("message");
		System.err.println(message);
		String trace = (String) errorAttributes.get("trace");
		//打印堆栈，调用链
		if (StringUtils.isNotBlank(trace)) {
			message += String.format(" and trace %s", trace);
		}
		System.err.println(message);
		return "error";
	}
	
	private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
		return errorAttributes.getErrorAttributes(new ServletWebRequest(request), true);
	}

	
}
