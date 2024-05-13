package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll(SearchCustomerRequest request);
    Customer update(Customer customer);
    void deleteById(String id);
   void updateStatusById(String id, Boolean isMember);
}
