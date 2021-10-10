package com.elanza48.TMS.controller.mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping
public class GenericController {

  @GetMapping(produces = {"application/hal+json"})
  public Map<String, String> getMethodName() {

    Map<String, String> map = new HashMap<>();
    map.put("message:", "Welcome to the Tour Company !");
    map.put("link", "./user");
    return map;
  }
  
}
