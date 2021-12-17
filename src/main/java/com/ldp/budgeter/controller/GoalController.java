package com.ldp.budgeter.controller;

import com.ldp.budgeter.domain.GoalService;
import com.ldp.budgeter.domain.Result;
import com.ldp.budgeter.domain.ResultType;
import com.ldp.budgeter.model.Goal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goal")
public class GoalController {

    private final GoalService service;

    public GoalController(GoalService service){
        this.service = service;
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<Goal> findById(@PathVariable int goalId){
        Goal goal = service.findById(goalId);
        if(goal == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goal, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Goal> add(@RequestBody Goal goal){
        Result<Goal> result = service.add(goal);
        if(result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }


    @PutMapping("/{goalId}")
    public ResponseEntity<Void> update(@PathVariable int goalId, @RequestBody Goal goal){
        if(goalId != goal.getGoalId()){
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Result<Goal> result = service.update(goal);
        if(result.getType() == ResultType.INVALID){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteById(@PathVariable int goalId){
        if(service.deleteById(goalId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
