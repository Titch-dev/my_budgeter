package com.ldp.budgeter.data;

import com.ldp.budgeter.model.Transactions;

public interface TransactionsRepo {

    Transactions findById(int transId);

    Transactions add(Transactions transaction);

    boolean update(Transactions transaction);

    boolean deleteById(int transId);
}
