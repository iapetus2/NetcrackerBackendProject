package com.projectparty.controllers;

import com.projectparty.entities.Deal;
import com.projectparty.service.DealServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping(value = "/api/deal")
    public ResponseEntity<List<Deal>> read() {
        final List<Deal> deals = dealService.readAll();

        return deals != null &&  !deals.isEmpty()
                ? new ResponseEntity<>(deals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

