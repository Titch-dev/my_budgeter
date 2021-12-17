package com.ldp.budgeter.controller;

import com.ldp.budgeter.domain.Result;
import com.ldp.budgeter.domain.ResultType;
import com.ldp.budgeter.domain.TransactionsService;
import com.ldp.budgeter.model.Transactions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService service;

    public TransactionsController(TransactionsService service){
        this.service = service;
    }

    @GetMapping("/{customerId}/all")
    public List<Transactions> findAll(@PathVariable int customerId){
        return service.findAll(customerId);
    }

    @GetMapping("/{transId}")
    public ResponseEntity<Transactions> findById(@PathVariable int transId){
        Transactions transaction = service.findById(transId);

        if(transaction == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Transactions> add(@RequestBody Transactions transaction){
        Result<Transactions> result = service.add(transaction);
        if(result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{transId}")
    public ResponseEntity<Void> update(@PathVariable int transId, @RequestBody Transactions transaction){
        // immediately halt if conflicted id's
        if(transId != transaction.getTransId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<Transactions> result = service.update(transaction);
        if(result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{transId}")
    public ResponseEntity<Void> deleteById(@PathVariable int transId){
        if (service.deleteById(transId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

