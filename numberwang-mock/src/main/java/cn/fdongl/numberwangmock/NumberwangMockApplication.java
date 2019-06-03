package cn.fdongl.numberwangmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan(basePackages = "cn.fdongl.numberwangentity.entity")
public class NumberwangMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberwangMockApplication.class, args);
    }

}
