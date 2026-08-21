plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

dependencies {
  implementation(libs.commonmark)
}

gradlePlugin {
  plugins {
    create("bstdoomSiteContent") {
      id = "io.github.bstdoom.site-content"
      implementationClass = "io.github.bstdoom.buildlogic.SiteContentPlugin"
    }
  }
}
