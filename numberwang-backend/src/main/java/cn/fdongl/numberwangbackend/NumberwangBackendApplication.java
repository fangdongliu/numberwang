package cn.fdongl.numberwangbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EntityScan("cn.fdongl.numberwangentity.entity")
public class NumberwangBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(NumberwangBackendApplication.class, args);
    }



}
