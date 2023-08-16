package com.publisher.RegisterPublisher.Repository;

import com.publisher.RegisterPublisher.Entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRepository
        extends JpaRepository<App, Long> {
    @Query("SELECT a FROM App a WHERE a.app_bundle_id = ?1")
    Optional<App> findAppByAppBundleId(String app_bundle_id);
}
