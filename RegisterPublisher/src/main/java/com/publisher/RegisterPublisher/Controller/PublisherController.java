package com.publisher.RegisterPublisher.Controller;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.publisher.RegisterPublisher.RegisterPublisherApplication;
import com.publisher.RegisterPublisher.Service.AppService;
import com.publisher.RegisterPublisher.Entity.App;
import com.publisher.RegisterPublisher.Entity.Publisher;
import com.publisher.RegisterPublisher.Service.AzureBlobStorageService;
import com.publisher.RegisterPublisher.Service.PublisherService;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;




import static com.publisher.RegisterPublisher.HelperFunctions.AppBundleIdValidator.validatorFunction;

@RestController
@RequestMapping("")
public class PublisherController {


    private static final Logger logger = (Logger) LoggerFactory.getLogger(PublisherController.class);
    private final PublisherService publisherService;
    private final AppService appService;
    private final AzureBlobStorageService azureBlobStorageService;

    @Autowired
    public PublisherController(PublisherService publisherService, AppService appService, AzureBlobStorageService azureBlobStorageService) {
        this.publisherService = publisherService;
        this.appService = appService;
        this.azureBlobStorageService = azureBlobStorageService;
    }
    List<String> keys = Arrays.asList("url","title","summary","score","reviews","released","realInstalls","ratings","minInstalls","genreId");

    public void writeLogs(String metadata){
        JsonObject jsonObject = new JsonParser().parse(metadata).getAsJsonObject();
        JsonObject jsonObjectFinal = new JsonObject();
        jsonObjectFinal.add("app_bundle_id",jsonObject.get("appId"));
        LocalDateTime localDateTime = LocalDateTime.now();

        // Format the date and time components separately
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDateTime.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = localDateTime.format(timeFormatter);

        jsonObjectFinal.addProperty("date",formattedDate);
        jsonObjectFinal.addProperty("time",formattedTime);
        for(int i=0; i< keys.size(); i++){
            jsonObjectFinal.add(keys.get(i),jsonObject.get(keys.get(i)));
        }
        logger.info(jsonObjectFinal.toString());
        azureBlobStorageService.uploadFileLocally();
    }


    @GetMapping
    public String home(){
        return "HomePage";
    }
    @PostMapping("publish")
    public String addApp(@RequestBody Map<String, Object> data){
        String app_bundle_id = (String)data.get("app_bundle_id");
        String output = validatorFunction(app_bundle_id);
        System.out.println("Check 1");
        if(output.length()==0) {
            return "App ID is invalid";
        }else{
            Optional<App>appOptional = appService.findApp(app_bundle_id);
            if(appOptional.isPresent()){
                return "App already present in the database";
            }
        }
        publisherService.addNewApp(data);
        writeLogs(output);
        return output;
    }
    @GetMapping("apps/{email}")
    public List<App> showDetails(@PathVariable String email){
        List<App> apps = publisherService.getAppsByEmail(email);
        return apps;
    }
    @GetMapping("showpublishers")
    public List<Publisher> showPublishers(){
        List<Publisher> publishers = publisherService.getAllPublishers();
        return publishers;
    }





}
