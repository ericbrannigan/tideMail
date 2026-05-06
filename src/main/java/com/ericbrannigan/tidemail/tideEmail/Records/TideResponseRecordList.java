package com.ericbrannigan.tidemail.tideEmail.Records;

import java.util.List;

public record TideResponseRecordList(
  List<TidePredictionResponse> predictions
) {}
