package com.projectparty.controllers;

import com.projectparty.entities.Order;
import com.projectparty.messages.DealMessage;
import com.projectparty.messages.OrderMessage;
import com.projectparty.service.OrderService;
import com.projectparty.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = {"/api"})
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> save(@RequestBody Order order) {
        try {
            orderService.save(order);
        } catch (RuntimeException e) {

            return new ResponseEntity<>("Error while creating order",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) {
        final Order order = orderService.read(id);

        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(String.format("Order with %2d not found", id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders/rooms/{id}")
    public ResponseEntity<?> readAll(@PathVariable(name = "id") int id) {
        final List<OrderMessage> orders = orderService.readAllItemsById(id);

        if (orders != null && !orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no orders", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Order order) {
        final boolean updated = orderService.update(order, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(
                String.format("Error, order with id %2d not modified", id),
                HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int orderId) {
        final boolean deleted = orderService.delete(orderId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(
                String.format("Error, order with id %2d not modified", orderId),
                HttpStatus.NOT_MODIFIED);
    }
}

