package com.example.javabootpad.configtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public A myA() {
        System.out.println("##### Generating A");
        return new A();
    }



    @Bean
    public B myB(A myA) {
        System.out.println("##### Generating B");
        System.out.println(myA.toString());
        return new B(myA);
    }

    class A {
    }

    class B {
        A a;

        B(A a) {
            this.a = a;
        }
    }
}