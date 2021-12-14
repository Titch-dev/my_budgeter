package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Customer;

public interface CustomerRepo {

    Customer findById(int customerId);

    Customer add(Customer customer);

    boolean update(Customer customer);

    boolean deleteById(int customerId);

}
