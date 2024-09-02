package com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @ClassDescription:
 * 注解@Restcontroller相当于@Controller和@RequestBody的组合注解
 */
@RestController
@RequestMapping("/springboot")
public class SpringBootUnionController {
    @CrossOrigin
    @RequestMapping(value = "/test",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(){
        return "springboot install successfully!";
    }
}


