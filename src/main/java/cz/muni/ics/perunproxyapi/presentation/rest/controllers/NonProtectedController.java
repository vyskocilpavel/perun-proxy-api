package cz.muni.ics.perunproxyapi.presentation.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NonProtectedController {

    @ResponseBody
    @GetMapping(value="/non")
    public String getResponse() {
        return "No authentication needed";
    }

}
