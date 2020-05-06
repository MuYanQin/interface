package cn.qin;

import cn.qin.constancts.FrameworkConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(basePackages = FrameworkConstants.MAPPER_SCAN)
public class ApplicationEnter {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEnter.class,args);
    }
}