package com;

import com.base.dto.LoginAuthDto;
import com.shop.UserClientPermission;
import com.shop.annotation.NoNeedAccessAuthentication;
import com.shop.dto.AdminDto;
import com.shop.support.BaseController;
import com.shop.wrapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@RequestMapping("/product")
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
public class MdcApplication extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MdcApplication.class);

	@Autowired
	UserClientPermission userClientPermission;

	@RequestMapping(value = "/list")
	public  String queryOrder(){
		LoginAuthDto loginAuthDto = getLoginAuthDto();
		logger.info("当前登录用户的信息是:【{}】",new Object[]{loginAuthDto});
		return "orderInfo----------------";
	}

	@RequestMapping(value = "/feign")
	public  Wrapper<AdminDto> feign(@RequestParam(name = "username") String username){
		Wrapper<AdminDto> feignString= userClientPermission.testFeign(username);
		return feignString;
	}

	@RequestMapping(value = "/test")
	@NoNeedAccessAuthentication
	public  String test(@RequestParam(name = "username") String username){
		return "test  no  bearer"+username;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MdcApplication.class, args);
	}
}
