package org.datn.controller;

import lombok.extern.log4j.Log4j2;
import org.datn.service.Impl.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    )throws Exception{
//        log.info("log :"+new Date(dateFrom));
        return ResponseEntity.ok(statisticalService.getByDate(dateFrom,dateTo));
    }
}
