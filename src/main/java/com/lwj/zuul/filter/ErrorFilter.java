package com.lwj.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 自定义实现zuul过滤器，异常处理
 * @author linwenjie
 *
 */
public class ErrorFilter extends ZuulFilter {

	/**
	 * 是否开启过滤器
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 自己的业务逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("Error Zuul Filter...");
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		//下面的逻辑应该是异常的处理
		System.err.println(throwable.getCause().getMessage());
		return null;
	}

	/**
	 * 过滤器类型：pre,route,post,error
	 */
	@Override
	public String filterType() {
		return "error";
	}

	/**
	 * 执行顺序，数值越小，优先级越高
	 */
	@Override
	public int filterOrder() {
		return 100;
	}

}
