package com.publisher.RegisterPublisher.Service;

import com.publisher.RegisterPublisher.Entity.App;
import com.publisher.RegisterPublisher.Repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    @Autowired
    private final AppRepository appRepository;

    public AppService(AppRepository appRepository){this.appRepository=appRepository;}

    public List<App> getAllApps(){
        List<App> apps = appRepository.findAll();
        return apps;
    }

    public Optional<App> findApp(String app_bundle_id){
        Optional<App> app = appRepository.findAppByAppBundleId(app_bundle_id);
        return app;
    }

}
