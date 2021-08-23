package com.vm.test;

import com.vm.test.component.TestBean1;
import com.vm.test.spring.SpringContextHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

//	@Resource
//	private TestBean1 testBean1;

	@Test
	void contextLoads() {
		System.out.println("start test");
//		TestBean1 testBean1 = SpringContextHolder.getApplicationContext().getBean(TestBean1.class);
//		testBean1.setName("oo");
//		System.out.println(testBean1.getName());
	}

}
