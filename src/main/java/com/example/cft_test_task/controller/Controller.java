package com.example.cft_test_task.controller;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technics")
public class Controller {
    @PutMapping
    public ResponseEntity<Boolean> add(){

    }
    @PostMapping()
    public ResponseEntity<Boolean> edit(){

    }
    @DeleteMapping()
    public ResponseEntity<Boolean> delete(){

    }
    @GetMapping()
    public ResponseEntity<List<TechnicsEntity>> getByType(){

    }
    @GetMapping()
    public ResponseEntity<TechnicsEntity> getById(){

    }
}
