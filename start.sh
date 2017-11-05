#!/bin/bash

/home/vlad/Doc/scala/spark-2.2.0-bin-hadoop2.7/bin/spark-submit \
  --class "SimpleApp" \
  --master local[4] \
  --driver-memory 5g\
  target/scala-2.11/simple-project_2.11-1.0.jar

#((./start.sh | tee stdout.log) 3>&1 1>&2 2>&3 | tee stderr.log) &> all.log

