package com.lwj.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 自定义实现zuul过滤器
 * @author linwenjie
 *
 */
public class IpFilter extends ZuulFilter {

	
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
		System.out.println("Per Zuul Filter...");
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.set("msg", "hello zuul filter");
		String param = ctx.getRequest().getParameter("testParam");
		if(param != null && param.equals("haha")) {
			//阻止请求继续向下传递，但是不会阻止其他的过滤器继续执行
			ctx.setSendZuulResponse(false);
			//阻止forward请求
			ctx.set("sendForwardFilter.ran", false);
			ctx.getResponse().setContentType("application/json; charset=utf-8");
			ctx.setResponseBody("返回信息");
			//通过ctx的传值，让其他过滤器不再执行
			ctx.set("isSuccess", false);
		}
		return null;
	}

	/**
	 * 过滤器类型：pre,route,post,error
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 执行顺序，数值越小，优先级越高
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

}
