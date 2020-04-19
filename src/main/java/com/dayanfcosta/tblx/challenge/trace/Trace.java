package com.dayanfcosta.tblx.challenge.trace;

import static com.dayanfcosta.tblx.challenge.shared.Constants.GPS_DATA_COLLECTION;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = GPS_DATA_COLLECTION)
class Trace {

  @Id
  private final String id;
  private final long delay;
  private final long lineId;
  private final long stopId;
  private final long blockId;
  private final long vehicleId;
  private final boolean atStop;
  private final long direction;
  private final long timestamp;
  private final String operator;
  private final double latitude;
  private final double longitude;
  private final boolean congestion;
  private final LocalDate timeFrame;
  private final long vehicleJourneyId;
  private final String journeyPatternId;

  @PersistenceConstructor
  public Trace(final String id, final long delay, final long lineId, final long stopId, final long blockId, final long vehicleId,
      final boolean atStop, final long direction, final long timestamp, final String operator, final double latitude,
      final double longitude, final boolean congestion, final LocalDate timeFrame, final long vehicleJourneyId,
      final String journeyPatternId) {
    this.id = id;
    this.delay = delay;
    this.lineId = lineId;
    this.stopId = stopId;
    this.atStop = atStop;
    this.blockId = blockId;
    this.vehicleId = vehicleId;
    this.direction = direction;
    this.timestamp = timestamp;
    this.operator = operator;
    this.latitude = latitude;
    this.longitude = longitude;
    this.timeFrame = timeFrame;
    this.congestion = congestion;
    this.vehicleJourneyId = vehicleJourneyId;
    this.journeyPatternId = journeyPatternId;
  }

  public String getId() {
    return id;
  }

  public long getDelay() {
    return delay;
  }

  public long getLineId() {
    return lineId;
  }

  public long getStopId() {
    return stopId;
  }

  public long getBlockId() {
    return blockId;
  }

  public long getVehicleId() {
    return vehicleId;
  }

  public boolean isAtStop() {
    return atStop;
  }

  public long getDirection() {
    return direction;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getOperator() {
    return operator;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public boolean isCongestion() {
    return congestion;
  }

  public LocalDate getTimeFrame() {
    return timeFrame;
  }

  public long getVehicleJourneyId() {
    return vehicleJourneyId;
  }

  public String getJourneyPatternId() {
    return journeyPatternId;
  }
}
