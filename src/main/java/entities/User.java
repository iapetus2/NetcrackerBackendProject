package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class User {

    @NonNull
    private AtomicInteger userId;
    private String userName;
    private Long cash;
    private List<Order> orders;
}
