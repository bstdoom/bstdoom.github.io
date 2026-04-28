# run the kobweb server on the configured port in `./site`
serve:
    cd ./site && kobweb run

# export the kobweb site to `./site/.kobweb/site`
export:
    cd ./site && kobweb export --layout static

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
