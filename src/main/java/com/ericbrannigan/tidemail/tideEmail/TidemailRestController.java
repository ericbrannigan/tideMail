package com.ericbrannigan.tidemail.tideEmail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TidemailRestController {

  private final TidemailFormatter tidemailFormatter;
  private final NoaaTideService noaaTideService;
  private final EmailService emailService;

  public TidemailRestController(
    NoaaTideService noaaTideService,
    TidemailFormatter tidemailFormatter,
    EmailService emailService
  ) {
    this.noaaTideService = noaaTideService;
    this.tidemailFormatter = tidemailFormatter;
    this.emailService = emailService;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void sendEmailOnStartup() {
    String date = LocalDate.now(ZoneId.of("America/New_York")).format(
      DateTimeFormatter.ofPattern("yyyyMMdd")
    );
    TideResponseRecordList tideResponse = noaaTideService.getPredictions(
      "8531680",
      date
    );
    StationResponseListRecord stationResponse = noaaTideService.getStationInfo(
      "8531680"
    );
    String body = tidemailFormatter.format(tideResponse, stationResponse);
    emailService.sendTideMail("pkmntrnreric@gmail.com", body);
  }

  @GetMapping("/")
  public String getTides(
    @RequestParam(defaultValue = "8531680") String stationID
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
