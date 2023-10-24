package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="University info from http://universities.hipolabs.com/")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService universityQueryService;

    @Operation(summary = "Get the university information with a certain name", description = "Documented here: http://universities.hipolabs.com/search")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversity(
        @Parameter(name="name", description = "name of the university", example="Harvard, Stanford") @RequestParam String name
    ) throws JsonProcessingException {
        log.info("getUniversity: name = {}", name);
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }

}