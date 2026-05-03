package com.ericbrannigan.tidemail.tideEmail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;


@RestController
public class TidemailRestController {

    private final TidemailFormatter tidemailFormatter;
    private final NoaaTideService noaaTideService;

    public TidemailRestController(NoaaTideService noaaTideService, TidemailFormatter tidemailFormatter){
        this.noaaTideService = noaaTideService;
        this.tidemailFormatter = tidemailFormatter;
    }
    
    

    @GetMapping("/")
    public String getTides(
            @RequestParam(defaultValue = "8518750") String stationID){

        String date = LocalDate.now(ZoneId.of("America/New_York")).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        TideResponse tideResponse = noaaTideService.getPredictions(stationID, date);
        return tidemailFormatter.format(tideResponse);
    }
} 

