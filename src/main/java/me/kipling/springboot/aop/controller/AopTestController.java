package me.kipling.springboot.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zkn on 2016/11/19.
 */
@RestController
@RequestMapping("/aop")
public class AopTestController {

	@RequestMapping("/testBeforeService.do")
	public String testBeforeService(String key, String value) {
		return "key=" + key + "  value=" + value;
	}
}