/*
 * Copyright (c) 2000-2016 Citrix Online LLC
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO CITRIX ONLINE
 * AND CONSTITUTES A VALUABLE TRADE SECRET.  Any unauthorized use,
 * reproduction, modification, or disclosure of this program is
 * strictly prohibited.  Any use of this program by an authorized
 * licensee is strictly subject to the terms and conditions,
 * including confidentiality obligations, set forth in the applicable
 * License and Co-Branding Agreement between Citrix Online LLC and
 * the licensee.
 */
package com.cloudnative.video;

import com.cloudnative.video.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceFacade implements UserService{

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;


    public User defaultUser(String name){
        return new User();
    }

    @HystrixCommand(fallbackMethod = "defaultUser")
    public User getUserByName(String name) {
        return restTemplate.getForObject("http://user/users/{name}", User.class,name);
    }
}