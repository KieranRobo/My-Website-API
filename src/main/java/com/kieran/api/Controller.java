package com.kieran.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
    public String test(@PathVariable(value="name") String name) {
        return "Hello there " + name + "!";
    }

}
