package com.publisher.RegisterPublisher.Controller;

import com.publisher.RegisterPublisher.Service.AppService;
import com.publisher.RegisterPublisher.Entity.App;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("showapps")
    public List<App> showAllApps(){
        List<App> apps = appService.getAllApps();
        return apps;
    }
}
