package com.publisher.RegisterPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private final AppRepository appRepository;

    public AppService(AppRepository appRepository){this.appRepository=appRepository;}


}
