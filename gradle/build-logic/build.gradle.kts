plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

gradlePlugin {
  plugins {
    create("bstdoomSiteContent") {
      id = "io.github.bstdoom.site-content"
      implementationClass = "io.github.bstdoom.buildlogic.SiteContentPlugin"
    }
  }
}
