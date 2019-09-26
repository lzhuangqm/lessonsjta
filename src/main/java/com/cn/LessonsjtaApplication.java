package com.cn;

/**
 * JTA测试
 */
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.transaction.annotation.*;

@SpringBootApplication
//@EnableTransactionManagement

public class LessonsjtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LessonsjtaApplication.class, args);
    }
}
