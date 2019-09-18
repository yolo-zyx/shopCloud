package com;

import com.base.dto.LoginAuthDto;
import com.shop.UserPermission;
import com.shop.support.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 *
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@RequestMapping("/order")
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
public class OmcApplication extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OmcApplication.class);

	@Autowired
	UserPermission  userPermission;

	@RequestMapping(value = "/list")
	public  String queryOrder(){
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		logger.info("当前登录用户的信息是:【{}】",new Object[]{loginAuthDto});
		return "orderInfo----------------";
	}

	@RequestMapping(value = "/feign")
	public  String feign(){
		String feignString=userPermission.testFeign("1111");
		return feignString;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OmcApplication.class, args);
	}
}
