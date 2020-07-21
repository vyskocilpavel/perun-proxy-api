package cz.muni.ics.perunproxyapi.presentation.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ba")
public class BasicAuthProtectedController {

    @ResponseBody
    @GetMapping(value="/test")
    public String getResponse() {
        return "Authenticated, welcome!";
    }

}
