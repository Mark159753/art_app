package com.example.core.until

import android.content.Context
import android.content.res.Resources
import android.graphics.*


private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val (height: Int, width: Int) = options.run { outHeight to outWidth }
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {

        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2

        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}

fun decodeSampledBitmapFromResource(
        res: Resources,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
): Bitmap {
    return BitmapFactory.Options().run {
        inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, this)
        inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
        inJustDecodeBounds = false

        BitmapFactory.decodeResource(res, resId, this)
    }
}

fun generateCircleBitmap(context: Context, circleColor: Int, diameterDP: Float, text: String?): Bitmap? {
    val textColor = -0x1
    val metrics = Resources.getSystem().displayMetrics
    val diameterPixels = diameterDP * (metrics.densityDpi / 160f)
    val radiusPixels = diameterPixels / 2

    // Create the bitmap
    val output = Bitmap.createBitmap(diameterPixels.toInt(), diameterPixels.toInt(),
            Bitmap.Config.ARGB_8888)

    // Create the canvas to draw on
    val canvas = Canvas(output)
    canvas.drawARGB(0, 0, 0, 0)

    // Draw the circle
    val paintC = Paint()
    paintC.isAntiAlias = true
    paintC.color = circleColor
    canvas.drawCircle(radiusPixels, radiusPixels, radiusPixels, paintC)

    // Draw the text
    if (text != null && text.isNotEmpty()) {
        val paintT = Paint()
        paintT.color = textColor
        paintT.isAntiAlias = true
        paintT.textSize = (radiusPixels * 1.5).toFloat()
        val typeFace = Typeface.createFromAsset(context.assets, "fonts/google_sans_regular.ttf")
        paintT.typeface = typeFace
        val textBounds = Rect()
        paintT.getTextBounds(text, 0, text.length, textBounds)
        canvas.drawText(text, radiusPixels - textBounds.exactCenterX(), radiusPixels - textBounds.exactCenterY(), paintT)
    }
    return output
}