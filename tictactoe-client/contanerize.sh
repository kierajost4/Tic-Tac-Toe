#!/bin/bash

docker build -t clientimage . 
docker run  clientimage
