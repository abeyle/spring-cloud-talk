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

import java.util.List;
import java.util.stream.Collectors;

import com.cloudnative.video.model.User;
import com.cloudnative.video.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    private final static Logger log = LoggerFactory.getLogger(VideoController.class);

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoStore videoStore;

    @RequestMapping("/videos")
    public List<Video> getAllVideos(@RequestParam(required = false) String user) {
        if (user != null) {
            return getUsersVideos(name);
        }
        return videoStore.getAll();
    }

    public List<Video> getUsersVideos(String name) {
        User user = userService.getUserByName(name);
        return videoStore.getAll()
                .stream()
                .filter(video -> user.id == video.userId)
                .collect(Collectors.<Video> toList());
    }

}
