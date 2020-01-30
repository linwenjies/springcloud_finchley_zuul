package com.lwj.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 自定义实现zuul过滤器
 * @author linwenjie
 *
 */
public class DemoFilter extends ZuulFilter {

	/**
	 * 是否开启过滤器
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getBoolean("isSuccess", true);
	}

	/**
	 * 自己的业务逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("Route Zuul Filter...");
		RequestContext ctx = RequestContext.getCurrentContext();
		//各个过滤器之间的传值是通过RequestContext实现的，原理是使用的ThreadLocal
		System.out.println(ctx.get("msg"));
		return null;
	}

	/**
	 * 过滤器类型：pre,route,post,error
	 */
	@Override
	public String filterType() {
		return "route";
	}

	/**
	 * 执行顺序，数值越小，优先级越高
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

}
