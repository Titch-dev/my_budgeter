package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.CustomerBudgetRepo;
import com.ldp.budgeter.model.Customer;
import com.ldp.budgeter.model.CustomerBudget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBudgetService {

//        private final CustomerBudgetRepo repo;
//
//        public CustomerBudgetService(CustomerBudgetRepo repo){
//            this.repo = repo;
//        }
//
//        public List<CustomerBudget> findAllById(int customerId){
//            return repo.findAllById(customerId);
//        }
//
//        public Result<CustomerBudget> add(CustomerBudget customerBudget){
//            Result<CustomerBudget> result = validate(customerBudget);
//        }
//
//        private Result<CustomerBudget> validate(CustomerBudget customerBudget){
//            Result<CustomerBudget> result = new Result<>();
//            if(customerBudget == null){
//                result.addMessage("CustomerBudget object cannot be null", ResultType.INVALID);
//                return result;
//            }
//            if(customerBudget.getCustomerId() <= 0 || customerBudget.getBudgetId() <= 0){
//                result.addMessage("customerId or budgetId has to be set", ResultType.INVALID);
//            }
//
//        }

}
