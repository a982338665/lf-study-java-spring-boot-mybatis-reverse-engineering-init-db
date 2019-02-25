package pers.li.aseckill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

/**
 * 要将spring boot自带的DataSourceAutoConfiguration禁掉，
 * 因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@Slf4j
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 加上@PostConstruct注解来制定该方法在初始化之后调用
	 */
	@PostConstruct
	public void init() {
		log.error("init...");
	}
}
