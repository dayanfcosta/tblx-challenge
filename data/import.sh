#! /bin/bash

mongoimport --type csv --db gps-api --collection gps-data --host mongodb \
  --columnsHaveTypes \
  --fields="timestamp.auto(),lineId.auto(),direction.auto(),journeyPatternId.auto(),timeFrame.date(2006-01-02),vehicleJourneyId.auto(),operator.auto(),congestion.boolean(),longitude.double(),latitude.auto(),delay.auto(),blockId.auto(),vehicleId.auto(),stopId.auto(),atStop.boolean()" \
  --file /data/siri.20121106.csv
