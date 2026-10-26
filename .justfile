
_list:
  just -l

# run the kobweb server on the configured port in `./site`
serve:
    ./gradlew :site:kobwebStart -PkobwebEnv=DEV -PkobwebRunLayout=FULLSTACK

# export the kobweb site to `./site/.kobweb/site`
export:
    ./gradlew :site:kobwebExport

# export the site and preview it over python3 on port 9358
preview:
    just export
    echo "Preview at http://localhost:9358"
    cd ./site/.kobweb/site && python3 -m http.server 9358

# stop any kobweb/python preview servers still listening on ports 9357 or 9358
stop:
    @for port in 9357 9358; do \
      pids="$({ lsof -ti tcp:${port} -sTCP:LISTEN || true; } | tr '\n' ' ')"; \
      if [ -n "$pids" ]; then \
        echo "Stopping processes on port ${port}: ${pids}"; \
        kill ${pids} || true; \
      else \
        echo "No process listening on port ${port}"; \
      fi; \
    done

roq-serve:
  ./gradlew :roq:roqServe

roq-serve-dev:
  ./gradlew :roq:quarkusDev --no-configuration-cache

roq-generate:
  ./gradlew :roq:roqGenerate

scan:
   lsof -nP -iTCP:8082 -sTCP:LISTEN 2>/dev/null | head -20; rg -n "roq-serve|quarkus.http.port|8082" .justfile roq/src/main/resources/application.properties
