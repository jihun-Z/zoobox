package com.example.zoobox.controller;

import com.example.zoobox.service.Service;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ApiController {


        @Autowired
        private Service service;

        @GetMapping("/api")

        public ModelAndView saveInfo() {
            ModelAndView mv=new ModelAndView();
            HashMap result=service.saveInfo();
            mv.addObject("info",result);

            return mv ;



    }
}
