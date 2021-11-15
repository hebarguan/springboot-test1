package com.vm.test;

import com.vm.test.component.TestBean1;
import org.kie.api.cdi.KContainer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.vm.test.mapper"})
@KContainer
public class TestApplication implements CommandLineRunner {

	public static void main(String[] args) throws IOException {
		 ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
		 TestBean1 testBean1 = context.getBean(TestBean1.class);

		 System.out.println(testBean1.getName());

	}

	@Override
	public void run(String... args) throws Exception {

	    System.out.println("doSomething after springBoot startUp");
	}
}
