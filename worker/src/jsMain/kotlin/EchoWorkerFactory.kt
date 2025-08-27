package io.github.bstdoom.worker

import com.varabyte.kobweb.worker.OutputDispatcher
import com.varabyte.kobweb.worker.WorkerFactory
import com.varabyte.kobweb.worker.WorkerStrategy
import com.varabyte.kobweb.worker.createPassThroughSerializer

internal class EchoWorkerFactory : WorkerFactory<String, String> {
    override fun createStrategy(postOutput: OutputDispatcher<String>) = WorkerStrategy<String> { input ->
        postOutput(input) // Add real worker logic here
    }

    override fun createIOSerializer() = createPassThroughSerializer()
}
