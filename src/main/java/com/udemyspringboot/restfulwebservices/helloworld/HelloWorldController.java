package com.udemyspringboot.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/basicauth")
    public String basicAuthCheck(){
        return "Success";
    }

    @GetMapping(path = "hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world bean");
    }

    @GetMapping(path = "hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null,
                "Default", locale);
//        return "Hello World V2";
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldWithPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello %s", name));
    }
}
