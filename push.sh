#!/bin/bash

docker buildx create --use
docker buildx build --platform linux/amd64,linux/arm64 -t thalessantanna/demo-health-database:0.0.2 \
  --push .