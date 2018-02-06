package com.umut.learnspringboot.config;

import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by Umut Uzun on 2/6/2018.
 */

@Configuration
@ApplicationPath("/")
public class ResteasyConfig extends Application {
}
