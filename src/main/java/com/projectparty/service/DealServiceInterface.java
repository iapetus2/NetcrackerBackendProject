package com.projectparty.service;

import com.projectparty.entities.Deal;
import java.util.List;

public interface DealServiceInterface {

    void save(Deal deal);

    List<Deal> readAll();

    Deal read(int id);

    boolean update(Deal deal, int id);

    boolean delete(int id);

}