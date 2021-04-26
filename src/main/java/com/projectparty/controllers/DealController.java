package com.projectparty.controllers;

import com.projectparty.entities.Deal;
import com.projectparty.messages.DealMessage;
import com.projectparty.service.DealServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
public class DealController {

    private final DealServiceImpl dealService;

    @Autowired
    public DealController(DealServiceImpl dealService) {
        this.dealService = dealService;
    }

    @PostMapping(value = "/api/deal")
    public ResponseEntity<?> save(@RequestBody Deal deal) {
        try {
            dealService.save(deal);
        } catch (RuntimeException e) {

            return new ResponseEntity<>("Error while creating deal",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/deal/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) {
        final Deal deal = dealService.read(id);

        return deal != null
                ? new ResponseEntity<>(deal, HttpStatus.OK)
                : new ResponseEntity<>(String.format("Deal with id %2d is not found", id),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/deal/rooms/{id}")
    public ResponseEntity<?> readAll(@PathVariable(name = "id") int id) {
        final List<Deal> deals = dealService.readAllItemsById(id);

        if (deals != null && !deals.isEmpty()) {
            final List<DealMessage> messages = deals
                    .stream()
                    .map(DealMessage::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(messages, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(String.format("Deal with id %2d is not found", id),
                    HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/api/deal/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Deal deal) {
        final boolean updated = dealService.update(deal, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(String.format("Deal with id %2d is not modified", id),
                HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/deal/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = dealService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(String.format("Deal with id %2d is not deleted", id),
                HttpStatus.NOT_MODIFIED);
    }
}


