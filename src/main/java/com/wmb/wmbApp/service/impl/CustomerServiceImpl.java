package com.wmb.wmbApp.service.impl;

import com.wmb.wmbApp.dto.request.NewCustomerRequest;
import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.dto.response.CustomerResponse;
import com.wmb.wmbApp.entity.Customer;
import com.wmb.wmbApp.repository.CustomerRepository;
import com.wmb.wmbApp.service.CustomerService;
import com.wmb.wmbApp.service.UserService;
import com.wmb.wmbApp.spesification.CustomerSpecification;
import com.wmb.wmbApp.utils.ValidationUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer create(NewCustomerRequest customerRequest) {
        validationUtil.validate(customerRequest);

        Customer newCustomer = Customer.builder()
                .name(customerRequest.getName())
                .mobilePhone(customerRequest.getMobilePhone())
                .isMember(customerRequest.getIsMember())
                .build();

        return customerRepository.saveAndFlush(newCustomer);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CustomerResponse> getAll(SearchCustomerRequest customerRequest) {

        log.info("SearchCustomerRequest: {}", customerRequest);

        if(customerRequest.getPage() <= 0){
            customerRequest.setPage(1);
        }

        String validSortBy = "name";

        Sort sort = Sort.by(Sort.Direction.fromString(customerRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of((customerRequest.getPage() - 1), customerRequest.getSize(), sort);

        Specification<Customer> specification = CustomerSpecification.getSpecification(customerRequest);

        Page<Customer> customers = customerRepository.findAll(specification, pageable);

        log.info("Specification: {}", specification);
        log.info("Pageable: {}", pageable);
        log.info("Found customers: {}", customers.getContent());

        return customers.map(this::parseCustomertoCustomerResponse);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatusById(String id, Boolean isMember) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateStatus(id, isMember);
    }

    public Customer findByIdOrThrowNotFound(String id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
    }

    private CustomerResponse parseCustomertoCustomerResponse(Customer customer){
        String userId;
        if (customer.getUserAccount() == null){
            userId = null;
        } else {
            userId = customer.getUserAccount().getId();
        }

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobilePhone(customer.getMobilePhone())
                .isMember(customer.getIsMember())
                .userAccountId(userId)
                .build();
    }
}
