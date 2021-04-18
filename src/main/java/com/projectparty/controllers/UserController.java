package com.projectparty.controllers;

import com.projectparty.dao.TradingItemDao;
import com.projectparty.entities.User;
import com.projectparty.response.UserDataResponse;
import com.projectparty.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Transactional
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    TradingItemDao tradingItemDao;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/user")
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id) {
        final User user = userService.read(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/user")
    public ResponseEntity<List<User>> read() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/user/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
        final boolean updated = userService.update(user, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/api/user/{id}/cash")
    public ResponseEntity<?> updateCash(@PathVariable(name = "id") int id, @RequestBody User user) {
        User userFromDb = userService.read(id);
        userFromDb.setCash(user.getCash() + userFromDb.getCash());
        final boolean updated = userService.update(userFromDb, id);
        Map<String, Integer> items = new HashMap<>();
        for(Integer key : userFromDb.getItems().keySet()){
          items.put(tradingItemDao.read(key).getItemName(), userFromDb.getItems().get(key));
        }
        Map<String, Integer> frozenItems = new HashMap<>();
        for(Integer key : userFromDb.getFrozenItems().keySet()){
            frozenItems.put(tradingItemDao.read(key).getItemName(), userFromDb.getFrozenItems().get(key));
        }
        return updated
                ? ResponseEntity.ok(new UserDataResponse(
                userFromDb.getUsername(),
                userFromDb.getCash(),
                userFromDb.getFrozenCash(),
                items,
                frozenItems))
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

