package cn.fdongl.numberwangfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = "cn.fdongl.numberwangmock")
public class NumberwangFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberwangFrontendApplication.class, args);
    }

}
