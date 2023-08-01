package com.publisher.RegisterPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.publisher.RegisterPublisher.AppBundleIdValidator.validatorFunction;

@RestController
@RequestMapping("")
public class PublisherController {

    private final PublisherService publisherService;
    private final AppRepository appRepository;
    @Autowired
    public PublisherController(PublisherService publisherService, AppRepository appRepository) {
        this.publisherService = publisherService;
        this.appRepository = appRepository;
    }
    @PostMapping("publish")
    public String addApp(@RequestBody Map<String, Object> data){
        String publisher_name = (String)data.get("name");
        String publisher_email = (String)data.get("email");
        String app_bundle_id = (String)data.get("app_bundle_id");
        String app_name = (String)data.get("app_name");
        String output = validatorFunction(app_bundle_id);


        if(output.length()==0) {
            System.out.println("Check 5");
            return "App ID is invalid";
        }else{
            Optional<App>appOptional = appRepository.findAppByAppBundleId(app_bundle_id);
            if(appOptional.isPresent()){
                return "App already present in the database";
            }
        }
        publisherService.addNewApp(data);
        return output;
    }
    @GetMapping("apps/{email}")
    public List<App> showDetails(@PathVariable String email){
        List<App> apps = publisherService.getAppsByEmail(email);
        return apps;
    }
}
