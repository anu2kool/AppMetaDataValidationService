package com.publisher.RegisterPublisher.Service;

import com.publisher.RegisterPublisher.Entity.App;
import com.publisher.RegisterPublisher.Entity.Publisher;
import com.publisher.RegisterPublisher.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addNewApp(Map<String, Object> data){
        String publisher_email = (String)data.get("email");
        Optional<Publisher> publisherOptional = publisherRepository.findPublisherByEmail(publisher_email);
        if(publisherOptional.isPresent()){
            System.out.println("Already present");
            App new_app = new App();
            new_app.setApp_bundle_id((String)data.get("app_bundle_id"));
            new_app.setApp_name((String)data.get("app_name"));
            publisherOptional.get().getApps().add(new_app);
            publisherRepository.save(publisherOptional.get());
        }else{
            System.out.println("Not present");
            Publisher new_publisher = new Publisher();
            new_publisher.setPublisher_email((String)data.get("email"));
            new_publisher.setPublisher_name((String)data.get("name"));
            App new_app = new App();
            new_app.setApp_name((String)data.get("app_name"));
            new_app.setApp_bundle_id((String)data.get("app_bundle_id"));
            new_publisher.getApps().add(new_app);
            publisherRepository.save(new_publisher);
        }

    }
    public List<App> getAppsByEmail(String email){
        Optional<Publisher> publisherOptional = publisherRepository.findPublisherByEmail(email);
        if(publisherOptional.isPresent()){
            return publisherOptional.get().getApps();
        }
        return List.of();
    }

    public List<Publisher> getAllPublishers(){
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers;
    }

}
