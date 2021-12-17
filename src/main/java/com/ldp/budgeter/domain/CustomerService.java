package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.CustomerRepo;
import com.ldp.budgeter.model.Customer;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final CustomerRepo repo;

    public CustomerService(CustomerRepo repo){
        this.repo = repo;
    }

    public Customer findById(int customerId){
        return repo.findById(customerId);
    }

    public Result<Customer> add(Customer customer){
        Result<Customer> result = validate(customer);
        if (result.getType() != ResultType.SUCCESS){
            return result;
        }

        Customer c = repo.add(customer);
        result.setPayload(c);
        return result;
    }

    public Result<Customer> update(Customer customer){
        Result<Customer> result = validate(customer);
        if (result.getType() != ResultType.SUCCESS){
            return result;
        }

        if (customer.getCustomerId() <= 0){
            result.addMessage("CustomerId must be set in order to update", ResultType.INVALID);
            return result;
        }

        if (!repo.update(customer)){
            result.addMessage("Customer Id " + customer.getCustomerId() + " not found.", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int customerId){
        return repo.deleteById(customerId);
    }

    private Result<Customer> validate(Customer customer){
        Result<Customer> result = new Result<>();

        if (customer == null){
            result.addMessage("customer cannot be null", ResultType.INVALID);
            return result;
        }

        if (customer.getFirstName() == null || customer.getFirstName().trim().length() == 0){
            result.addMessage("customer name cannot be blank", ResultType.INVALID);
        }

        if (customer.getEmail() == null || customer.getEmail().trim().length() == 0){
            result.addMessage("customer email cannot be left blank", ResultType.INVALID);
        }

        return result;
    }
}
