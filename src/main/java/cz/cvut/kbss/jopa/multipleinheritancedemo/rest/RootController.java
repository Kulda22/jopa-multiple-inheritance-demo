package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController
{
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getWelcomeMessage(){
        return "Welcome to Multiple Inheritance Demo. \r\r\r\r\rSee \\audio-books or \\copiers to browse the data.";
    }
}
