package com.publisher.RegisterPublisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface PublisherRepository
        extends JpaRepository<Publisher, Long> {
    @Query("SELECT p FROM Publisher p WHERE p.publisher_email = ?1")
    Optional<Publisher> findPublisherByEmail(String email);
}
