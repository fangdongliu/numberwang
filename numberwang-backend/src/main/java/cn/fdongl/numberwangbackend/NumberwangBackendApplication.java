package cn.fdongl.numberwangbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EntityScan("cn.fdongl.numberwangentity.entity")
public class NumberwangBackendApplication {


    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","E:\\winutils-master\\hadoop-3.0.0");
        System.setProperty("HADOOP_USER_NAME","root");
        SpringApplication.run(NumberwangBackendApplication.class, args);
    }

}
