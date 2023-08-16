package com.publisher.RegisterPublisher.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlockBlobItem;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class AzureBlobStorageService {

    String sas_token =
    public void uploadFileLocally(){
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("")
                .sasToken(sas_token)
                .buildClient();
        String  containerName = "metadatavalidation";
        BlobContainerClient  containerClient = blobServiceClient.getBlobContainerClient(containerName);
        System.out.println("Check 3");
        //Locally running path
        String local_file_path = "/Users/anukool.dwivedi/Desktop/OnBoardingProject/AppMetaDataValidationService/RegisterPublisher/logs/requests.log";
        //Kubernetes running path
        //String local_file_path = "/Users/anukool.dwivedi/Desktop/OnBoardingProject/AppMetaDataValidationService/RegisterPublisher/logs/requests.log";
        BlobClient blobClient = containerClient.getBlobClient("requests.log");
        blobClient.uploadFromFile(local_file_path,true);
        System.out.println("Logs updated!!");
    }
}
