package kz.segizbay.spring_library_project.contorllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    @Value("${hello}")
    private String hello;

    @GetMapping
    public String index(){
        System.out.println(hello);
        return "index";
    }
}
