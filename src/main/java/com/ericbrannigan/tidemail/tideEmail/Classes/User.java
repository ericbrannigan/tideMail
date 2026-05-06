package com.ericbrannigan.tidemail.tideEmail.Classes;

import com.ericbrannigan.tidemail.tideEmail.Interfaces.UserInterface;
import java.util.UUID;

public class User implements UserInterface {

  UUID userID;
  String email;
  String tideStation;
  String emailTime;
  int numberOfDays;
  boolean pauseEmails;

  public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    this.userID = userID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTideStation() {
    return tideStation;
  }

  public void setTideStation(String tideStation) {
    this.tideStation = tideStation;
  }

  public String getEmailTime() {
    return emailTime;
  }

  public void setEmailTime(String emailTime) {
    this.emailTime = emailTime;
  }

  public int getNumberOfDays() {
    return numberOfDays;
  }

  public void setNumberOfDays(int numberOfDays) {
    this.numberOfDays = numberOfDays;
  }

  public boolean getPauseEmails() {
    return pauseEmails;
  }

  public void setPauseEmails(boolean pauseEmails) {
    this.pauseEmails = pauseEmails;
  }
}
