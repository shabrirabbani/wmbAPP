package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.request.NewCustomerRequest;
import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.dto.response.CustomerResponse;
import com.wmb.wmbApp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer create(NewCustomerRequest customer);
    Customer getById(String id);
    Page<CustomerResponse> getAll(SearchCustomerRequest request);
    Customer update(Customer customer);
    void deleteById(String id);
    void updateStatusById(String id, Boolean isMember);
}
