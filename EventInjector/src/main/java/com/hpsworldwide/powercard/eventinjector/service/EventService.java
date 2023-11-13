package com.hpsworldwide.powercard.eventinjector.service;

import com.hpsworldwide.powercard.eventinjector.entity.Event;
import com.hpsworldwide.powercard.eventinjector.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<Event> injectEvent(Event event, int repeat) {
        List<Event> result = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            Event clonedEvent = new Event(event);
           // System.out.println("*************"+ LocalTime.now());
            eventRepository.save(clonedEvent);
            result.add(clonedEvent);
        }
        return result;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Event findEventById(Long eventId) {
        return eventRepository.findById(String.valueOf(eventId)).orElse(null);
    }

    /*public void save(Event event, int numberOfEvents) {
        for (int i = 0; i < numberOfEvents; i++) {
            eventRepository.save(event);
        }
    }*/

}
