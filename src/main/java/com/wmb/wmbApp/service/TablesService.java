package com.wmb.wmbApp.service;

import com.wmb.wmbApp.entity.Tables;
import org.springframework.stereotype.Service;

@Service
public interface TablesService {
    Tables create(Tables tables);
    Tables getById(String id);

}
