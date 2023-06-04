package com.assignment.ziploan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
public class TestController {

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public ResponseEntity<?> heartBeat() {
        return new ResponseEntity<>(Collections.singletonMap("status", "OK"), HttpStatus.OK);
    }
}
