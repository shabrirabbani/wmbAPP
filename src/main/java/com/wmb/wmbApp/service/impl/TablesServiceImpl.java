package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.entity.Tables;
import com.wmb.wmbApp.repository.TablesRepository;
import com.wmb.wmbApp.service.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TablesServiceImpl implements TablesService {

    private final TablesRepository tablesRepository;

    @Override
    public Tables create(Tables tables) {
        return tablesRepository.saveAndFlush(tables);
    }

    @Override
    public Tables getById(String id) {
        Optional<Tables> optionalTables = tablesRepository.findById(id);
        if (optionalTables.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tables not found");
        }
        return optionalTables.get();
    }
}
