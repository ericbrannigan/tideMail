package com.ericbrannigan.tidemail.tideEmail.Classes;

import com.ericbrannigan.tidemail.tideEmail.Interfaces.UserInterface;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserInterface {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userID;

  private String email;
  private String tideStation;
  private String emailTime;
  private int numberOfDays;
  private boolean pauseEmails;

  public User() {}

  public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    if (userID == null) {
      throw new IllegalArgumentException("userID cannot be null.");
    }
    this.userID = userID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty.");
    }

    if (!email.contains("@")) {
      throw new IllegalArgumentException("Enter a valid E-Mail.");
    }
    this.email = email;
  }

  public String getTideStation() {
    return tideStation;
  }

  public void setTideStation(String tideStation) {
    if (tideStation == null || tideStation.isBlank()) {
      throw new IllegalArgumentException(
        "Tide Station cannot be null or left blank."
      );
    }
    this.tideStation = tideStation;
  }

  public String getEmailTime() {
    return emailTime;
  }

  public void setEmailTime(String emailTime) {
    if (emailTime == null || emailTime.isBlank()) {
      throw new IllegalArgumentException(
        "Time for emails cannot be null or left blank"
      );
    }
    this.emailTime = emailTime;
  }

  public int getNumberOfDays() {
    return numberOfDays;
  }

  public void setNumberOfDays(int numberOfDays) {
    if (numberOfDays < 1 || numberOfDays > 14) {
      throw new IllegalArgumentException(
        "Number of days cannot be less than 1 or greater than 14 days."
      );
    }
    this.numberOfDays = numberOfDays;
  }

  public boolean getPauseEmails() {
    return pauseEmails;
  }

  public void setPauseEmails(boolean pauseEmails) {
    if (pauseEmails != true || pauseEmails != false) {
      throw new IllegalArgumentException(
        "Pause Emails must be set to Yes or No."
      );
    }
    this.pauseEmails = pauseEmails;
  }
}
