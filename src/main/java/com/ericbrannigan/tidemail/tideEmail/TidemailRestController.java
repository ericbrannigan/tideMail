package com.ericbrannigan.tidemail.tideEmail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TidemailRestController {

  private final TidemailFormatter tidemailFormatter;
  private final NoaaTideService noaaTideService;

  public TidemailRestController(
    NoaaTideService noaaTideService,
    TidemailFormatter tidemailFormatter
  ) {
    this.noaaTideService = noaaTideService;
    this.tidemailFormatter = tidemailFormatter;
  }

  @GetMapping("/")
  public String getTides(
    @RequestParam(defaultValue = "8518750") String stationID
  ) {
    String date = LocalDate.now(ZoneId.of("America/New_York")).format(
      DateTimeFormatter.ofPattern("yyyyMMdd")
    );

    TideResponseRecordList tideResponse = noaaTideService.getPredictions(
      stationID,
      date
    );
    StationResponseListRecord stationResponse = noaaTideService.getStationInfo(
      stationID
    );
    return tidemailFormatter.format(tideResponse, stationResponse);
  }
}
