package com.projectparty.controllers;

import com.projectparty.entities.Deal;
import com.projectparty.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealController {
    private final DealService dealService;

    @Autowired
    public DealController(DealService tealService) {
        this.dealService = tealService;
    }

    @PostMapping(value = "/api/item")
    public ResponseEntity<?> save(@RequestBody Deal item) {
        dealService.save(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/item/{id}")
    public ResponseEntity<Deal> read(@PathVariable(name = "id") int id) {
        final Deal teal = dealService.read(id);

        return teal != null
                ? new ResponseEntity<>(teal, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/item")
    public ResponseEntity<List<Deal>> read() {
        final List<Deal> teals = dealService.readAll();

        return teals != null &&  !teals.isEmpty()
                ? new ResponseEntity<>(teals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/item/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Deal teal) {
        final boolean updated = dealService.update(teal, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/item/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = dealService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
