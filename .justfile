# run the kobweb server on port 9357  in `site/`
serve:
    cd ./site && kobweb run

# export the kobweb site to `site/.kobweb/site`
export:
    cd site && kobweb export

# serve the exported site on port 9358
static:
    php -S localhost:9358 -t site/.kobweb/site
