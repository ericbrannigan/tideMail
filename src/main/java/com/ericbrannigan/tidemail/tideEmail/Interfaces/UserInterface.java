package com.ericbrannigan.tidemail.tideEmail.Interfaces;
import java.util.UUID;

public interface UserInterface {
    void setUserID(UUID userID);
    UUID getUserID();
    void setEmail(String email);
    String getEmail();
    void setTideStation(String tideStation);
    String getTideStation();
    void setEmailTime(String emailTime);
    String getEmailTime();
    void setNumberOfDays(int numberOfDays);
    int getNumberOfDays();
    void setPauseEmails(boolean pauseEmails);
    boolean getPauseEmails();
}
