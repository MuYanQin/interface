package cn.qin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaAuditing
@MapperScan(basePackages = "cn.qin.dao")
public class ApplicationEnter {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEnter.class,args);
    }
}