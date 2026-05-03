package com.ericbrannigan.tidemail.tideEmail;

import java.util.List;

public record TideResponseRecordList(
  List<TidePredictionResponse> predictions
) {}
