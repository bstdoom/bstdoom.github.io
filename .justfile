
_list:
  just -l

roq-serve:
  ./gradlew roqServe

roq-serve-dev:
  ./gradlew quarkusDev --no-configuration-cache

roq-generate:
  ./gradlew roqGenerate

scan:
   lsof -nP -iTCP:8082 -sTCP:LISTEN 2>/dev/null | head -20; rg -n "roq-serve|quarkus.http.port|8082" .justfile src/main/resources/application.properties
