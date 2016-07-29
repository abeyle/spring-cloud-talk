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

import java.util.Arrays;
import java.util.List;

import com.cloudnative.video.model.Video;
import org.springframework.stereotype.Service;

@Service
public class VideoStore {


    public List<Video> getAll(){
        return Arrays.asList(new Video("Spring cloud",1,"https://youtu.be/F49_LJJPs_s"),
                             new Video("AWS ECS",2,"https://youtu.be/JIuyCgNg8io"));
    }

}
