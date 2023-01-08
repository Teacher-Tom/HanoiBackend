package com.example.arith.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @Author 12042
 * @create 2023/1/8 11:01
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }
    // 配置swagger基本信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("likailing", "https://github.com/Teacher-Tom", "1204232472@qq.com");
        return new ApiInfo(
                "汉诺塔后端api",
                "包含汉诺塔问题",
                "1.0",
                "none",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
