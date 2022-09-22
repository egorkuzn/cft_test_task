package com.example.cft_test_task.controller;

import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.service.TechnicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technics")
public class TechnicsController {
    final TechnicsService technicsService;

    @Autowired
    public TechnicsController(TechnicsService technicsService){
        this.technicsService = technicsService;
    }

    @PutMapping
    public ResponseEntity<Boolean> add(@RequestBody TechnicsRequest technicsRequest){
        return ResponseEntity.ok().body(technicsService.add(technicsRequest));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<Boolean> edit(@PathVariable Long id, @RequestParam String field, @RequestParam String variable){
        return ResponseEntity.ok().body(technicsService.edit(id, field, variable));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(technicsService.delete(id));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TechnicsResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(technicsService.getById(id));
    }

    @GetMapping( "/type/{type}")
    public ResponseEntity<List<TechnicsResponse>> getByType(@PathVariable String type){
        return ResponseEntity.ok().body(technicsService.getByType(type));
    }
}
