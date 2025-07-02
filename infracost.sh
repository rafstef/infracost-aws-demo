#!/bin/bash

BRANCH=$(git rev-parse --abbrev-ref HEAD)

case "$BRANCH" in
  develop) PROJECT="develop" ;;
  release) PROJECT="release" ;;
  main)    PROJECT="prd" ;;
  *) echo "Branch $BRANCH non gestito."; exit 1 ;;
esac

infracost breakdown \
  --config-file=.infracost-config.yml \
  --project="$PROJECT" \
  --format=json \
  --out-file=/tmp/infracost.json

infracost upload --path=/tmp/infracost.json
