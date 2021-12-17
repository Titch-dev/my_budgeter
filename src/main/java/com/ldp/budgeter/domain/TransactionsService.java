package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.CustomerBudgetRepo;
import com.ldp.budgeter.data.TransactionsRepo;
import com.ldp.budgeter.model.CustomerBudget;
import com.ldp.budgeter.model.Transactions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    private final TransactionsRepo repo;
    private final CustomerBudgetRepo customerBudgetRepo;

    public TransactionsService(TransactionsRepo repo, CustomerBudgetRepo customerBudgetRepo){
        this.repo = repo;
        this.customerBudgetRepo = customerBudgetRepo;
    }

    public List<Transactions> findAll(int customerId){
        return repo.findAll(customerId);
    }

    public Transactions findById(int transId) {
        return repo.findById(transId);
    }

    public Result<Transactions> add(Transactions transactions){
        Result<Transactions> result = validate(transactions);
        if (result.getType() != ResultType.SUCCESS){
            return result;
        }
        Transactions t = repo.add(transactions);
        //        add customerId and BudgetId to CustomerBudget repo if does not exist
//            CustomerBudget newEntry = new CustomerBudget();
//            newEntry.setCustomerId(transactions.getCustomerId());
//            newEntry.setBudgetId(transactions.getBudgetId());
//            if(transactions.getDirection() == "in"){
//                newEntry.setAccurateBal(transactions.getTransAmount());
//            } else {
//                newEntry.setAccurateBal(newEntry.getAccurateBal() - transactions.getTransAmount());
//            }
//            try{
//                customerBudgetRepo.add(newEntry);
//            } catch err  {
//        if does already exist update entry in CustomerBudget - or + depending on In or out field
        result.setPayload(t);
        return result;
    }

    public Result<Transactions> update(Transactions transactions){
        Result<Transactions> result = validate(transactions);
        if (result.getType() != ResultType.SUCCESS){
            return result;
        }

        if (transactions.getTransId() <= 0){
            result.addMessage("TransactionsId must be set in order to update", ResultType.INVALID);
            return result;
        }

        if(!repo.update(transactions)){
            result.addMessage("Transaction Id " + transactions.getTransId() + " not found.", ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int transId){
        return repo.deleteById(transId);
    }

    private Result<Transactions> validate(Transactions transactions){
        Result<Transactions> result = new Result<>();

        if(transactions == null){
            result.addMessage("Transaction cannot be null", ResultType.INVALID);
            return result;
        }

        if(!transactions.getDirection().equals("in") && !transactions.getDirection().equals("out")){
            result.addMessage("Direction must be set to 'in' or 'out'", ResultType.INVALID);
        }

        if(transactions.getBudgetId() <= 0 || transactions.getBudgetId() > 12){
            result.addMessage("you have to select a valid budgetID between 1-12", ResultType.INVALID);
        }

        if(transactions.getCustomerId() <= 0){
            result.addMessage("Customer Id cannot be blank", ResultType.INVALID);
        }

        return result;

    }

}
