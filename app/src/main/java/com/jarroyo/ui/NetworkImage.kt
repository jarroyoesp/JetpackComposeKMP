package com.jarroyo.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

/**
 * A wrapper around [CoilImage] setting a default [contentScale] and showing
 * an indicator when loading disney poster images.
 *
 * @see CoilImage https://github.com/skydoves/landscapist#coil
 */
@Composable
fun NetworkImage(
  url: String,
  modifier: Modifier,
  circularRevealedEnabled: Boolean = false,
  contentScale: ContentScale = ContentScale.Crop
) {

  CoilImage(
    imageModel = url,
    modifier = modifier,
    contentScale = contentScale,
    circularRevealedEnabled = circularRevealedEnabled,
    circularRevealedDuration = 250,
    failure = {
      Text(
        text = "image request failed.",
        style = MaterialTheme.typography.body2
      )
    }
  )
}