package me.kipling.springboot.aop.config;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@Component
@Aspect
public class WebControllerAop {

	/*
	 * 定义切点
	 */
	// 匹配com.zkn.learnspringboot.web.controller包及其子包下的所有类的所有方法
	@Pointcut("execution(* me.kipling.springboot.aop.controller..*.*(..))")
	public void executeService() {
	}

	/**
	 * 前置通知，方法调用前被调用
	 * 
	 * 
	 * 通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等。
	 * 通过RequestContextHolder来获取请求信息，Session信息。
	 * 
	 * @param joinPoint
	 */
	@Before("executeService()")
	public void doBeforeAdvice(JoinPoint joinPoint) {
		System.out.println("我是前置通知!!!");
		// 获取目标方法的参数信息
		Object[] obj = joinPoint.getArgs();
		// AOP代理类的信息
		joinPoint.getThis();
		// 代理的目标对象
		joinPoint.getTarget();
		// 用的最多 通知的签名
		Signature signature = joinPoint.getSignature();
		// 代理的是哪一个方法
		System.out.println(signature.getName());
		// AOP代理类的名字
		System.out.println(signature.getDeclaringTypeName());
		// AOP代理类的类（class）信息
		signature.getDeclaringType();
		// 获取RequestAttributes
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		// 从获取RequestAttributes中获取HttpServletRequest的信息
		HttpServletRequest request = (HttpServletRequest) requestAttributes
				.resolveReference(RequestAttributes.REFERENCE_REQUEST);
		// 如果要获取Session信息的话，可以这样写：
		// HttpSession session = (HttpSession)
		// requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> parameterMap = Maps.newHashMap();
		while (enumeration.hasMoreElements()) {
			String parameter = enumeration.nextElement();
			parameterMap.put(parameter, request.getParameter(parameter));
		}
		String str = JSON.toJSONString(parameterMap);
		if (obj.length > 0) {
			System.out.println("请求的参数信息为：" + str);
		}
	}
}