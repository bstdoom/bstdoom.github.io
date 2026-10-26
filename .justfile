
_list:
  just -l

roq-serve:
  ./gradlew :roq:roqServe

roq-serve-dev:
  ./gradlew :roq:quarkusDev --no-configuration-cache

roq-generate:
  ./gradlew :roq:roqGenerate

scan:
   lsof -nP -iTCP:8082 -sTCP:LISTEN 2>/dev/null | head -20; rg -n "roq-serve|quarkus.http.port|8082" .justfile roq/src/main/resources/application.properties
