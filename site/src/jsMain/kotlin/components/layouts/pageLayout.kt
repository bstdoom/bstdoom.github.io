package io.github.bstdoom.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Text

@Composable
@Layout
fun PageLayout(ctx: PageContext, content: @Composable ColumnScope.() -> Unit) {
    Box {
        Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Header {
                Text("This is the header")
            }
            content()
        }
    }
    Footer()
}
