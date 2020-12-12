package com.jarroyo.jetpackcomposekmp.ui.composables

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LifecycleOwnerAmbient

private val AmbientBackPressedDispatcher =
    staticAmbientOf<OnBackPressedDispatcherOwner?> { null }

// Simple implementation of OnBackPressedCallback interface. Holds a reference to a lambda that's
// used to describe the onBackPressed action and calls it at the right instance (when
// handleOnBackPressed is called)
private class ComposableBackHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit

    override fun handleOnBackPressed() {
        onBackPressed()
    }
}

@Composable
internal fun handler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = (AmbientBackPressedDispatcher.current ?: return).onBackPressedDispatcher
    // remember{} is a helper composable that calculates the value passed to it only during the
    // first composition. It then returns the same value for every subsequent composition. In the
    // example below, it initializes the value of ComposableBackHandler() and does it only during
    // the first composition. It's important to understand that the subsequent screens where this
    // value is passed to are still allowed to modify the value (depending on whether it has
    // mutable properties).
    val handler = remember { ComposableBackHandler(enabled) }
    // The onCommmit block is a lifecycle effect composable that is called every time this
    // composition is committed. In simpler words, the first onCommit block below will run each
    // time the value of dispatcher changes (in addition to the first time the handler composable
    // is called)
    onCommit(dispatcher) {
        dispatcher.addCallback(handler)
        // The onDispose block inside the onCommit effect is called to do any clean up(if
        // necessary) for the side effect that executed inside onCommit.
        onDispose { handler.remove() }
    }
    onCommit(enabled) {
        handler.isEnabled = enabled
    }
    onCommit(onBackPressed) {
        handler.onBackPressed = onBackPressed
    }
}


@Composable
internal fun BackButtonHandler(onBackPressed: () -> Unit) {
    // Providers allows you to map values to Ambients. You can think of binding values to a key
    // represented by Ambients.

    // What are Ambients?
    // In Compose, we typically pass data through the composition tree explicitly through means of
    // parameters to composable functions. This is inline with the principles of unidirection
    // data flow that Compose heavily recommends using. There are situations where this won't
    // always be possible. For these cases, [Ambient]s can be used as an implicit way to have
    // data flow through a composition.

    // Another way to think about Providers is that I can get access to a value in the middle of
    // a composition, without having to pass the value in. Some other examples of Providers and
    // Ambients are ContextAmbient(to get access to the context), CoroutineContextAmbient, etc.
    Providers(
        AmbientBackPressedDispatcher provides LifecycleOwnerAmbient.current as ComponentActivity
    ) {
        handler {
            onBackPressed()
        }
    }
}