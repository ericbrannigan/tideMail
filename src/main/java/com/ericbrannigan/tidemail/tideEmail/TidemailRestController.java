package com.ericbrannigan.tidemail.tideEmail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;


@RestController
public class TidemailRestController {
    private final NoaaTideService noaaTideService;

    public TidemailRestController(NoaaTideService noaaTideService){
        this.noaaTideService = noaaTideService;
    }
    
    

    @GetMapping("/")
    public TideResponse getTides(
            @RequestParam(defaultValue = "8518750") String stationID){
        String date = LocalDate.now(ZoneId.of("America/New_York")).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return noaaTideService.getPredictions(stationID, date);
    }
}

