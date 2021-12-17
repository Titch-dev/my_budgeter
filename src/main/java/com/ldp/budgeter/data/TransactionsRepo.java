package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Transactions;

import java.util.List;

public interface TransactionsRepo {

    List<Transactions> findAll(int customerId);

    Transactions findById(int transId);

    Transactions add(Transactions transaction);

    boolean update(Transactions transaction);

    boolean deleteById(int transId);
}
