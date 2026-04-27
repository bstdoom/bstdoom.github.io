package io.github.bstdoom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import kotlinx.browser.document
import kotlinx.browser.window

@InitSilk
fun initStyles(ctx: InitSilkContext) {
  ctx.stylesheet.registerStyleBase("html, body") { Modifier.fillMaxHeight() }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
  DisposableEffect(Unit) {
    // Keep only the dynamic background rotation here; static assets are now injected via Kobweb's generated head.
    val body = document.body
    val previousBackgroundImage = body?.style?.backgroundImage
    val previousBackgroundPosition = body?.style?.backgroundPosition
    val previousBackgroundRepeat = body?.style?.backgroundRepeat
    val previousBackgroundSize = body?.style?.backgroundSize
    val previousBackgroundAttachment = body?.style?.backgroundAttachment
    body?.classList?.add("theme-body")

    val slides = listOf(
      "/assets/background/bg1.png",
      "/assets/background/bg2.png",
      "/assets/background/bg3.png",
      "/assets/background/bg4.png",
    )
    var index = 0

    fun applySlide() {
      body?.style?.backgroundImage = "url('${slides[index]}')"
      body?.style?.backgroundPosition = "center"
      body?.style?.backgroundRepeat = "no-repeat"
      body?.style?.backgroundSize = "cover"
      body?.style?.backgroundAttachment = "fixed"
    }

    applySlide()
    val intervalId = window.setInterval({
      index = (index + 1) % slides.size
      applySlide()
    }, 12000)

    onDispose {
      window.clearInterval(intervalId)
      if (body != null) {
        if (previousBackgroundImage != null) body.style.backgroundImage = previousBackgroundImage
        if (previousBackgroundPosition != null) body.style.backgroundPosition = previousBackgroundPosition
        if (previousBackgroundRepeat != null) body.style.backgroundRepeat = previousBackgroundRepeat
        if (previousBackgroundSize != null) body.style.backgroundSize = previousBackgroundSize
        if (previousBackgroundAttachment != null) body.style.backgroundAttachment = previousBackgroundAttachment
      }
    }
  }

  SilkApp {
    content()
  }
}
