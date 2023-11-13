package com.hpsworldwide.powercard.eventinjector.controller;

import org.springframework.web.bind.annotation.*;
import com.hpsworldwide.powercard.eventinjector.entity.Event;
import com.hpsworldwide.powercard.eventinjector.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/inject")
    public List<Event> injectEvent(@RequestBody Event event, @RequestParam int repeat) {
        return eventService.injectEvent(event, repeat);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
