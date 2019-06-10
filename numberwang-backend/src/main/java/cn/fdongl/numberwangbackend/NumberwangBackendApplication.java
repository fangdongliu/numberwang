package cn.fdongl.numberwangbackend;


import cn.fdongl.numberwangbackend.aware.UserArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableJpaAuditing
@EntityScan("cn.fdongl.numberwangentity.entity")
public class NumberwangBackendApplication implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(NumberwangBackendApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver());
    }
}
