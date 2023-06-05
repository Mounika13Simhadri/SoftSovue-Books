package com.application.publishers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.application.publishers.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {

}
