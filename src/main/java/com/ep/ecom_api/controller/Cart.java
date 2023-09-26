package com.ep.ecom_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cart {

    @GetMapping("/")
    public String check() {
        return "API is working!";
    }
    @GetMapping("/api/check")
    public String checkApi() {
        return "API is working!";
    }
    @GetMapping("/admin/additems")
    public String addItems() {
        return "API admin additem";
    }
    @GetMapping("/user/viewitem")
    public String viewItem() {
        return "API user viewItem";
    }
}