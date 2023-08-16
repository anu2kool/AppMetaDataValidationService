package com.publisher.RegisterPublisher.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="apps")
public class App {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long app_id;
    private String app_name;
    private String app_bundle_id;

    public App() {
    }

    public App(String app_name, String app_bundle_id) {
        this.app_name = app_name;
        this.app_bundle_id = app_bundle_id;
    }

    public long getApp_id() {
        return app_id;
    }

    public void setApp_id(long app_id) {
        this.app_id = app_id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_bundle_id() {
        return app_bundle_id;
    }

    public void setApp_bundle_id(String app_bundle_id) {
        this.app_bundle_id = app_bundle_id;
    }
}
