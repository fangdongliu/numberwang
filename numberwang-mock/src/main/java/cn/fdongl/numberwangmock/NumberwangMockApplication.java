package cn.fdongl.numberwangmock;

import cn.fdongl.numberwangmock.aware.UserArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Entity;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "cn.fdongl.numberwangentity.entity")
public class NumberwangMockApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(NumberwangMockApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver());
    }
}
