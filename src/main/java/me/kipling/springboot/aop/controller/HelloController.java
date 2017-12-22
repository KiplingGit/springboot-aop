package me.kipling.springboot.aop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * 
 * 
 * @author uatwz90560
 *
 */
@RestController
public class HelloController {

	protected static Logger logger = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("/")
	String home() {
		logger.debug("访问home");
		return "Hello World!";
	}

	@RequestMapping("/hello/{myName}")
	String index(@PathVariable String myName) {
		logger.debug("访问helloName,Name={}", myName);
		return "Hello " + myName + "!!!";
	}
}
