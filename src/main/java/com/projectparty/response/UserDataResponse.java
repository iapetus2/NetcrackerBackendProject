package com.projectparty.response;

import com.projectparty.entities.TradingItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@AllArgsConstructor //todo pick one
@NoArgsConstructor
public class UserDataResponse {

    private String username;

    private String email;

    private long cash;

    private long frozenCash;

    private Map<String, Integer> items;

    private Map<String, Integer> frozenItems;

    private Map<Integer, String> itemNames;

    public UserDataResponse(String username, long cash, long frozenCash, Map<String,
            Integer> items, Map<String, Integer> frozenItems){
        this.username = username;
        this.cash = cash;
        this.frozenCash = frozenCash;
        this.items = items;
        this.frozenItems = frozenItems;
    }
}
