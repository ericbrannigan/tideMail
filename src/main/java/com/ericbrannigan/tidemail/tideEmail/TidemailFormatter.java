package com.ericbrannigan.tidemail.tideEmail;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class TidemailFormatter {
    
    public String format(TideResponse tideResponse){
        StringBuilder sb = new StringBuilder();
        sb.append("<pre>");
        sb.append("Today's Tides for Your Area\n\n");
        sb.append(tideResponse.metadata().name());
        for (TidePrediction prediction : tideResponse.predictions()) {
            
            String type = prediction.type().equals("H") ? "High Tide" : "Low Tide";
            sb.append(type)
            .append(": ")
            .append(prediction.t())
            .append("   |   ")
            .append(prediction.v())
            .append(" ft\n");
        }
        sb.append("</pre>");
        return sb.toString();
    }
}
