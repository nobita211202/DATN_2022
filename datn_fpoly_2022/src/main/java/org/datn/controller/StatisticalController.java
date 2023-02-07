package org.datn.controller;

import lombok.extern.log4j.Log4j2;
import org.datn.service.Impl.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/api/statistical")
public class StatisticalController {
    @Autowired
    StatisticalService statisticalService;
    @GetMapping("/getByDate/{dayFrom}/{dayTo}")
    public ResponseEntity getDay(
            @PathVariable("dayFrom")String dateFrom,
            @PathVariable("dayTo")String dateTo
    ){
        return ResponseEntity.ok(statisticalService.getByDate(dateFrom,dateTo));
    }

    @GetMapping("/getCountAll")
    public ResponseEntity getCountAll(){
        return ResponseEntity.ok(statisticalService.getCoutnAll());
    }
    @GetMapping("/getCountByDate/{dayFrom}/{dayTo}")
    public ResponseEntity getCountByDate(
            @PathVariable("dayFrom")String dateFrom,
            @PathVariable("dayTo")String dateTo
    ){
        return ResponseEntity.ok(statisticalService.getCountByDate(dateFrom,dateTo));
    }
}
