package com.hpsworldwide.powercard.eventinjector.repository;

import com.hpsworldwide.powercard.eventinjector.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

}