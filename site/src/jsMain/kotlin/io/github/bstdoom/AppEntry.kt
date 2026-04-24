package io.github.bstdoom

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.w3c.dom.HTMLLinkElement

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("html, body") { Modifier.fillMaxHeight() }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    LaunchedEffect(Unit) {
        val head = document.head ?: return@LaunchedEffect
        if (document.getElementById("bstdoom-font-awesome-css") == null) {
            val link = document.createElement("link") as HTMLLinkElement
            link.id = "bstdoom-font-awesome-css"
            link.rel = "stylesheet"
            link.href = "/css/font-awesome.min.css"
            head.appendChild(link)
        }
        if (document.getElementById("bstdoom-theme-css") == null) {
            val link = document.createElement("link") as HTMLLinkElement
            link.id = "bstdoom-theme-css"
            link.rel = "stylesheet"
            link.href = "/css/theme.css"
            head.appendChild(link)
        }
        document.body?.classList?.add("theme-body")
    }

    SilkApp {
        content()
    }
}
