package com.workoutScheduler.planner.controllers;

import com.workoutScheduler.planner.dto.CreateExerciseRequest;
import com.workoutScheduler.planner.dto.DeleteExerciseRequest;
import com.workoutScheduler.planner.dto.UpdateExerciseRequest;
import com.workoutScheduler.planner.models.Exercise;
import com.workoutScheduler.planner.repo.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseRepository exerciseRepo;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody CreateExerciseRequest request){
        Exercise exercise = new Exercise();
        exercise.setCategory(request.getCategory());
        exercise.setDescription(request.getDescription());
        exercise.setName(request.getName());
        exercise.setMuscleGroup(request.getMuscleGroup());

        exerciseRepo.save(exercise);
        return ResponseEntity.ok("Exercise created successfully");
    }

    @GetMapping("/")
    public ResponseEntity<List<Exercise>> getExercises(){
        List<Exercise> exercises = exerciseRepo.findAll();
        return ResponseEntity.ok(exercises);
    }

    @DeleteMapping("/delete-exercise")
    public ResponseEntity<String> deleteExercise(@RequestBody DeleteExerciseRequest request){
        Optional<Exercise> exeOpt = exerciseRepo.findById(request.getEid());
        if(exeOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found");
        }
        exerciseRepo.deleteById(request.getEid());
        return ResponseEntity.ok("Exercise Successfully deleted");
    }

    @PutMapping("/update-exercise")
    public ResponseEntity<String> updateExercise(@RequestBody UpdateExerciseRequest request){
        Optional<Exercise> exeOpt = exerciseRepo.findById(request.getEid());
        if(exeOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found");
        }
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setMuscleGroup(request.getMuscleGroup());
        exercise.setDescription(request.getDescription());
        exercise.setCategory(request.getCategory());

        exerciseRepo.save(exercise);
        return ResponseEntity.ok("Exercise Successfully updated");
    }
}
