package com.jsonyao.mystock.sync;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.jsonyao.mystock.common", "com.jsonyao.mystock.sync"})
@MapperScan(basePackages = { "com.jsonyao.mystock.**.mapper" })
@ForestScan(basePackages = {"com.jsonyao.mystock.**.client"})
public class SyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyncApplication.class, args);
    }
}
