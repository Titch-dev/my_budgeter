package com.ldp.budgeter.controller;

import com.ldp.budgeter.domain.CustomerService;
import com.ldp.budgeter.domain.Result;
import com.ldp.budgeter.domain.ResultType;
import com.ldp.budgeter.domain.TransactionsService;
import com.ldp.budgeter.model.Customer;
import com.ldp.budgeter.model.Transactions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController{

    private final CustomerService service;
    private final TransactionsService transService;

    public CustomerController(CustomerService service, TransactionsService transService){
        this.service = service;
        this.transService = transService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable int customerId){
        Customer customer = service.findById(customerId);
        if(customer == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/{customerId}/all")
    public List<Transactions> findAll(@PathVariable int customerId){
        return transService.findAll(customerId);
    }

    @PostMapping()
    public ResponseEntity<Customer> add(@RequestBody Customer customer){
        Result<Customer> result = service.add(customer);
        if(result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> update(@PathVariable int customerId, @RequestBody Customer customer){
        // if an id conflict. will stop immediately
        if(customerId != customer.getCustomerId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<Customer> result = service.update(customer);
        if (result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable int customerId){
        if (service.deleteById(customerId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
