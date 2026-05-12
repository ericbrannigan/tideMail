package com.ericbrannigan.tidemail.tideEmail.Controller;
import com.ericbrannigan.tidemail.tideEmail.Records.TideResponseRecordList;
import com.ericbrannigan.tidemail.tideEmail.Services.NoaaTideService;
import com.ericbrannigan.tidemail.tideEmail.EmailFormatter.*;
import com.ericbrannigan.tidemail.tideEmail.Records.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.time.format.*;
import java.time.LocalDate;
import java.time.ZoneId;



@Controller
public class TidemailViewController {

    private final NoaaTideService noaaTideService;
    private final TidemailFormatter tidemailFormatter;

    public TidemailViewController(NoaaTideService noaaTideService, TidemailFormatter tidemailFormatter) {
        this.noaaTideService = noaaTideService;
        this.tidemailFormatter = tidemailFormatter;
    }

    @GetMapping("/")
    public String homePage(
            @RequestParam(required = false) String stationID,
            Model model) {
        model.addAttribute("pageTitle", "Tidemail");
        model.addAttribute("testBody", "Welcome to Tidemail!");

        if (stationID != null) {
            String date = LocalDate.now(ZoneId.of("America/New_York"))
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            TideResponseRecordList tideResponse = noaaTideService.getPredictions(stationID, date);
            StationResponseListRecord stationResponse = noaaTideService.getStationInfo(stationID);
            String body = tidemailFormatter.format(tideResponse, stationResponse);
            model.addAttribute("tideResults", body);
            model.addAttribute("stationID", stationID);
        }

        return "index";
    }
}
