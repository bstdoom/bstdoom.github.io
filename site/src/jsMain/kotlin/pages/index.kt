package io.github.bstdoom.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import io.github.bstdoom.components.sections.Footer
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
@Layout(".components.layouts.PageLayout")
fun HomePage() {
//    val worker = rememberWorker { EchoWorker { output -> console.log("Echoed: $output") } }
//    LaunchedEffect(Unit) {
//        worker.postInput("Hello, worker!")
//    }

    // TODO: Replace the following with your own content
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("THIS PAGE INTENTIONALLY LEFT BLANK")
    }

    Footer()
}
