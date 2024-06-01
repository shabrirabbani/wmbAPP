package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.constant.ResponseMessage;
import com.wmb.wmbApp.dto.request.NewCustomerRequest;
import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.dto.response.CommonResponse;
import com.wmb.wmbApp.dto.response.PagingResponse;
import com.wmb.wmbApp.entity.Customer;
import com.wmb.wmbApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> createNewCustomer(@RequestBody NewCustomerRequest customerRequest){
        Customer newCustomer = customerService.create(customerRequest);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully create new customer")
                .data(newCustomer)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id){
        Customer customerById = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customerById)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomer(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ){
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .Direction(direction)
                .name(name)
                .build();

        Page<Customer> customerAll = customerService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(customerAll.getTotalPages())
                .totalElements(customerAll.getTotalElements())
                .page(customerAll.getPageable().getPageNumber() + 1)
                .size(customerAll.getPageable().getPageSize())
                .hasNext(customerAll.hasNext())
                .hasPrevious(customerAll.hasPrevious())
                .build();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get all customer")
                .data(customerAll.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody Customer customer){
        Customer update = customerService.update(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update data")
                .data(update)
                .build();

        return ResponseEntity.ok(response);
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
