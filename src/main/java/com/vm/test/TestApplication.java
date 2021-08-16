package com.vm.test;

import com.vm.test.spi.IDriver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.util.ServiceLoader;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.vm.test.mapper"})
public class TestApplication implements CommandLineRunner {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	    System.out.println("doSomething after tomcat startup");
	}
}
