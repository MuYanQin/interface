package cn.qin.base.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.TimeZone;

/**
 * Created by LEO on 2015/9/15.
 */
@Configuration
@EnableScheduling
@Log4j
public class WebConfig extends WebMvcConfigurerAdapter {


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    RestTemplate restTemplate(){

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(20 * 1000);

        return new RestTemplate(httpRequestFactory);
    }
    @Bean
    public FilterRegistrationBean parameterFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ParameterFilter());
        registration.addUrlPatterns("/*");
        registration.setName("parameterFilter");
        registration.setOrder(20000);
        log.info("***************************parameterFilter初始化***************************");
        return registration;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }


}
