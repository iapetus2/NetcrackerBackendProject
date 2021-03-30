package com.projectparty.controllers;

import com.projectparty.entities.Order;
import com.projectparty.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = {"/api"})
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> save(@RequestBody Order order) {
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> read(@PathVariable(name = "id") int id) {
        final Order order = orderService.read(id);

        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> readAll() {
        final List<Order> orders = orderService.readAll();

        return orders != null &&  !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/orders/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Order order) {
        final boolean updated = orderService.update(order, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int orderId) {
        final boolean deleted = orderService.delete(orderId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

