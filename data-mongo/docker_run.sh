#!/usr/bin/env bash
docker run \
    --name mongo \
    -p 27017:27017 \
    -v $PWD/mongo_data:/data/db \
    -e MONGO_INITDB_ROOT_USERNAME=admin \
    -e MONGO_INITDB_ROOT_PASSWORD=secret \
    -d mongo
