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
//todo add error handling to every method, probably use https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
public class DealController {

    private final DealServiceImpl dealService; // todo use interface

    @Autowired
    public DealController(DealServiceImpl dealService) {
        this.dealService = dealService;
    }

    @PostMapping(value = "/api/deal")
    public ResponseEntity<?> save(@RequestBody Deal deal) {
        dealService.save(deal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/deal/{id}")
    public ResponseEntity<Deal> read(@PathVariable(name = "id") int id) {
        final Deal deal = dealService.read(id);

        return deal != null
                ? new ResponseEntity<>(deal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/deal/rooms/{id}")
    public ResponseEntity<List<DealMessage>> readAll(@PathVariable(name = "id") int id) {
        final List<Deal> deals = dealService.readAllItemsById(id);

        if(deals != null &&  !deals.isEmpty()){
            final List<DealMessage> messages = deals
                    .stream()
                    .map(DealMessage::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/api/deal/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Deal deal) {
        final boolean updated = dealService.update(deal, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/deal/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = dealService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

