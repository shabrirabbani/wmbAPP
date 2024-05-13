package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.entity.Customer;
import com.wmb.wmbApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public Customer createNewCustomer(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public Customer getCustomerById(@PathVariable String id){
        return customerService.getById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "mobilePhone", required = false) String phone,
            @RequestParam(name = "isMember", required = false) Boolean isMember
    ){

        SearchCustomerRequest searchCustomerRequest = SearchCustomerRequest.builder()
                .name(name)
                .phone(phone)
                .isMember(isMember)
                .build();

        return customerService.getAll(searchCustomerRequest);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public String deleteById(@PathVariable String id){
        customerService.deleteById(id);
        return "success delete customer";
    }

    @PutMapping(path = APIUrl.PATH_VAR_ID)
    public String updateCustomer(
            @PathVariable String id,
            @RequestParam(name = "isMember") Boolean isMember
    ){
        customerService.updateStatusById(id,isMember);
        return "success update member";
    }

}
