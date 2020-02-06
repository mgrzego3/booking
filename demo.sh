#!/bin/bash

rm ~/.h2/*
mvn package
java -jar target/rest-service-0.0.1-SNAPSHOT.jar &
sleep 5


curl "localhost:8080/screenings?date=2020-02-04&starttime=14:00&endtime=16:00"
curl "localhost:8080/screening/1"
curl "localhost:8080/reserve/1?name=Aaa&surname=Bbb&values=A,1,normal"