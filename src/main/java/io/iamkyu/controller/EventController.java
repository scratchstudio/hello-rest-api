package io.iamkyu.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import io.iamkyu.domain.Event;
import io.iamkyu.domain.EventRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
        Event savedEvent = eventRepository.save(event);
        URI uri = linkTo(EventController.class)
                .slash(savedEvent.getId())
                .toUri();

        return ResponseEntity.created(uri).body(event);
    }
}