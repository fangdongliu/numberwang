package cn.fdongl.numberwangfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "cn.fdongl.numberwangbackend")
public class NumberwangFrontendApplication {
    public static void main(String[] args) {
        SpringApplication.run(NumberwangFrontendApplication.class, args);
    }

}
