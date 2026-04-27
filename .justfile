# run the kobweb server on the configured port in `site/`
serve:
    cd ./site && kobweb run

# export the kobweb site to `site/.kobweb/site`
export:
    cd site && kobweb export

# export the site and preview it over python3 on port 9358
preview:
    cd site && kobweb export --layout static
    cd site/.kobweb/site && python3 -m http.server 9358
