package com.hpsworldwide.powercard.eventinjector.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Table(name = "EXTERNAL_EVENT")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "event_id")
    @GenericGenerator(name = "event_id", strategy = "increment")
    @GeneratedValue(generator = "event_id")
    private Long id;

    @Column(name = "channel")
    private String channel;

    @Column(name = "service")
    private String service;

    @Column(name = "status")
    private String status;

    @Column(name = "content")
    private String content;


    public Event(Event event) {
        this.channel = event.getChannel();
        this.service = event.getService();
        this.status = event.getStatus();
        this.content = event.getContent();
    }
    public Event() {}
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", channel='" + channel + '\'' + ", service='" + service + '\'' + ", status='" + status + '\'' + ", content='" + content + '\'' + '}';
    }
}
