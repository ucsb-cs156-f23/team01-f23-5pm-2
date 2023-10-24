package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
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



@Tag(name="Tides info from NOAA")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {
    @Autowired
    TidesQueryService tidesQueryService;

    @Operation(summary = "Gets tide information from beginDate to endDate at requested station", description = "JSON return format documented here: https://api.tidesandcurrents.noaa.gov/api/prod/")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
        @Parameter(name="beginDate", description="The start date, written in YYYYMMDD", example="20230624") @RequestParam String beginDate,
        @Parameter(name="endDate", description="The end date, written in YYYYMMDD", example="20230724") @RequestParam String endDate,
        @Parameter(name="station", description="The station recording the tides data, list of stations can be found at  https://tidesandcurrents.noaa.gov", example="9414290") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getTides:  beginDate={} endDate={} station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }
}
