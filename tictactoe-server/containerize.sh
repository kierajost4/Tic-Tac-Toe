#!/bin/bash

docker build -t serverimage . 
docker run -p 8080:8080 serverimage
