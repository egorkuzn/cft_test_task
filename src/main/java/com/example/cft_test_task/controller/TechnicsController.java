package com.example.cft_test_task.controller;

import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.service.TechnicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("technics")
@Api(value = "technics")
public class TechnicsController {
    final TechnicsService technicsService;

    @Autowired
    public TechnicsController(TechnicsService technicsService){
        this.technicsService = technicsService;
    }

    @PutMapping
    @ApiOperation("add")
    public ResponseEntity<Boolean> add(@RequestBody TechnicsRequest technicsRequest){
        return ResponseEntity.ok().body(technicsService.add(technicsRequest));
    }

    @PatchMapping("id/{id}")
    @ApiOperation("edit")
    public ResponseEntity<Boolean> edit(@PathVariable Long id, @RequestParam String field, @RequestParam String variable){
        return ResponseEntity.ok().body(technicsService.edit(id, field, variable));
    }

    @DeleteMapping("id/{id}")
    @ApiOperation("delete")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(technicsService.delete(id));
    }

    @GetMapping("id/{id}")
    @ApiOperation("get by id")
    public ResponseEntity<TechnicsResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(technicsService.getById(id));
    }

    @GetMapping( "type/{type}")
    @ApiOperation("get by type")
    public ResponseEntity<List<TechnicsResponse>> getByType(@PathVariable String type){
        return ResponseEntity.ok().body(technicsService.getByType(type));
    }
}
