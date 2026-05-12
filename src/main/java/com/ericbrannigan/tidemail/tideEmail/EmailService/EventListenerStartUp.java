package com.ericbrannigan.tidemail.tideEmail.EmailService;

import com.ericbrannigan.tidemail.tideEmail.EmailFormatter.TidemailFormatter;
import com.ericbrannigan.tidemail.tideEmail.EmailService.EmailService;
import com.ericbrannigan.tidemail.tideEmail.Records.StationResponseListRecord;
import com.ericbrannigan.tidemail.tideEmail.Records.TideResponseRecordList;
import com.ericbrannigan.tidemail.tideEmail.Services.NoaaTideService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerStartUp {

  private final EmailService emailService;
  private final TidemailFormatter tidemailFormatter;
  private final NoaaTideService noaaTideService;

  public EventListenerStartUp(
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
    try {
      String date = LocalDate.now(ZoneId.of("America/New_York")).format(
        DateTimeFormatter.ofPattern("yyyyMMdd")
      );
      TideResponseRecordList tideResponse = noaaTideService.getPredictions(
        "8531680",
        date
      );
      StationResponseListRecord stationResponse =
        noaaTideService.getStationInfo("8531680");
      String body = tidemailFormatter.format(tideResponse, stationResponse);
      emailService.sendTideMail("pkmntrnreric@gmail.com", body);
    } catch (Exception e) {
      System.err.println("Email failed: " + e.getMessage());
    }
  }
}
