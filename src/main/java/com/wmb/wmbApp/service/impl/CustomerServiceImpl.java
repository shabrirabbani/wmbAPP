package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.entity.Customer;
import com.wmb.wmbApp.repository.CustomerRepository;
import com.wmb.wmbApp.service.CustomerService;
import com.wmb.wmbApp.spesification.CustomerSpecification;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EntityManager entityManager;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Customer> getAll(SearchCustomerRequest request) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(request);
        if (request.getName() == null && request.getPhone() == null & request.getIsMember() == null){
            return customerRepository.findAll();
        }
        return customerRepository.findAll(customerSpecification);
    }

    @Override
    public Customer update(Customer customer) {
        findByIdOrThrowNotFound(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    public Customer findByIdOrThrowNotFound(String id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
    }

    @Override
    public void updateStatusById(String id, Boolean isMember) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateStatus(id, isMember);
    }
}
