package com.hzcf.getway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

/**
 * Create by hanlin on 2018年5月30日
 **/
@Component
public class ZuulFilterTest extends ZuulFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
	 * 在上例中，我们直接返回true，所以该过滤器总是生效。
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * run：过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，
	 * 不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，
	 * 当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
	 */
	@Override
	public Object run() {
		try {
			double a = 100/0;
			return a;
		} catch (Exception e) {
			logger.error("zuul异常",e);
			throw e;
		}
	}

	/**
	 * filterType： 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
	 * pre：可以在请求被路由之前调用 routing：在路由请求时候被调用 post：在routing和error过滤器之后被调用
	 * error：处理请求时发生错误时被调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * filterOrder：通过int值来定义过滤器的执行顺序
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

}
