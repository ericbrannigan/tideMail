package com.ericbrannigan.tidemail.tideEmail;
import java.util.List;

public record TideResponse(List<TidePrediction> predictions, TideMetadata metadata) {}
