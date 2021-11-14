package com.jarroyo.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.jarroyo.R
import com.skydoves.landscapist.CircularReveal
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
    // Crop, Fit, Inside, FillHeight, FillWidth, None
    contentScale = ContentScale.Crop,
    // shows an image with a circular revealed animation.
    circularReveal = CircularReveal(duration = 250),
    // shows a placeholder ImageBitmap when loading.
    placeHolder = ImageBitmap.imageResource(R.drawable.img_placeholder),
    // shows an error ImageBitmap when the request failed.
    error = ImageBitmap.imageResource(R.drawable.img_error)
  )
}