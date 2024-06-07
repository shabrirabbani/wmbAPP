package com.wmb.wmbApp.service;

import com.wmb.wmbApp.entity.Transtype;
import org.springframework.stereotype.Service;

@Service
public interface TranstypeService {
    Transtype create(Transtype transtype);
    Transtype getById(String id);
}
