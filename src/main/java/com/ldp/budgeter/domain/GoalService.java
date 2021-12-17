package com.ldp.budgeter.domain;

import com.ldp.budgeter.data.GoalRepo;
import com.ldp.budgeter.model.Goal;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    private final GoalRepo repo;

    public GoalService(GoalRepo repo){
        this.repo = repo;
    }

    public Goal findById(int goalId){
        return repo.findById(goalId);
    }

    public Result<Goal> add(Goal goal){
        Result<Goal> result = validate(goal);
        if(result.getType() != ResultType.SUCCESS){
            return result;
        }

        Goal g = repo.add(goal);
        result.setPayload(g);
        return result;
    }

    public Result<Goal> update(Goal goal){
        Result<Goal> result = validate(goal);
        if(result.getType() != ResultType.SUCCESS){
            return result;
        }

        if(goal.getGoalId() <= 0){
            result.addMessage("GoalId must be set in order to update", ResultType.INVALID);
        }

        if(!repo.update(goal)){
            result.addMessage("Goal Id " + goal.getGoalId() + " does not exist.", ResultType.NOT_FOUND);
            return result;
        }
        return result;
    }

    public boolean deleteById(int goalId){
        return repo.deleteById(goalId);
    }

    private Result<Goal> validate(Goal goal){
        Result<Goal> result = new Result<>();

        if(goal == null){
            result.addMessage("Goal cannot be null", ResultType.INVALID);
            return result;
        }

        if(goal.getGoalName() == null || goal.getGoalName().trim().length() == 0){
            result.addMessage("Goal name must be given and cannot be blank", ResultType.INVALID);
        }

        if(goal.getTargetAmount() <= 0){
            result.addMessage("You must set a positive int for targetAmount", ResultType.INVALID);
        }

        if(goal.getCustomerId() <= 0){
            result.addMessage("You must have a customerId with this transaction", ResultType.INVALID);
        }

        return result;
    }
}
