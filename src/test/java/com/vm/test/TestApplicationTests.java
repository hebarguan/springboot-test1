package com.vm.test;

import com.alibaba.fastjson.JSONObject;
import com.vm.test.component.TestBean1;
import com.vm.test.spring.SpringContextHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

class TestApplicationTests {

//	@Resource
//	private TestBean1 testBean1;

    public static void main(String[] args) {
		List<String> arr = new ArrayList<>();
		arr.add("guan");
		StringBuilder str = new StringBuilder();
		arr.forEach(str::append);
		System.out.println(JSONObject.toJSON(arr));
//		TestBean1 testBean1 = SpringContextHolder.getApplicationContext().getBean(TestBean1.class);
//		testBean1.setName("oo");
//		System.out.println(testBean1.getName());
	}

}
