#!/bin/sh

feature=$1
customer=$2
capFeature="$(tr '[:lower:]' '[:upper:]' <<< ${feature:0:1})${feature:1}"
capCustomer="$(tr '[:lower:]' '[:upper:]' <<< ${customer:0:1})${customer:1}"
echo "feature=$feature, customer=$customer"

rm ./app/build/outputs/apk/$feature$capCustomer/debug/app-$feature-$customer-debug.apk
./gradlew clean
./gradlew assemble$capFeature$capCustomer
#./gradlew uninstallDebug
#./gradlew installDebug
adb install -r ./app/build/outputs/apk/$feature$capCustomer/debug/app-$feature-$customer-debug.apk
