package com.hpsworldwide.powercard.eventinjector.controller;

import com.hpsworldwide.powercard.eventinjector.entity.Event;
import com.hpsworldwide.powercard.eventinjector.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/events")
public class InjectEvents {

    @Autowired
    private EventService eventService;

    @PostMapping("/")
    public ResponseEntity<String> postEvents(@RequestBody Event event, @RequestParam int numberOfEvents, @RequestParam long duration) {


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Record the start time
        Instant startTime = Instant.now();
        Runnable task = () -> {
                eventService.injectEvent(event, numberOfEvents);

        };

        ScheduledFuture<?> scheduledFuture = scheduler.schedule(task, 0, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            scheduledFuture.cancel(true);
            scheduler.shutdown();
            // Record the finish time
            Instant finishTime = Instant.now();

            // Print start and finish times
            System.out.println("Start Time: " + startTime);
            System.out.println("Finish Time: " + finishTime);

        }, duration, TimeUnit.SECONDS);

        return new ResponseEntity<>("Events injected successfully", HttpStatus.OK);
    }
}
