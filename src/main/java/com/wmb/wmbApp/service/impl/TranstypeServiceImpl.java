package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.entity.Tables;
import com.wmb.wmbApp.entity.Transtype;
import com.wmb.wmbApp.repository.TranstypeRepository;
import com.wmb.wmbApp.service.TablesService;
import com.wmb.wmbApp.service.TranstypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranstypeServiceImpl implements TranstypeService {

    private final TranstypeRepository transtypeRepository;

    @Override
    public Transtype create(Transtype transtype) {
        return transtypeRepository.saveAndFlush(transtype);
    }

    @Override
    public Transtype getById(String id) {
        Optional<Transtype> optionalTranstype = transtypeRepository.findById(id);
        if (optionalTranstype.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transtype not found");
        }
        return optionalTranstype.get();
    }
}
