package com.crypto.moto.ui.rating

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.crypto.moto.R

class CustomRatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }

    fun setRating(rating: Float) {
        val roundedRating = rating.roundToHalf()
        val filledRates = roundedRating.toInt()
        val halfRate = (roundedRating - filledRates).let { if (it > 0) 1 else 0 }
        val emptyRates = numberOfStars - filledRates - halfRate

        removeAllViews()
        repeat(filledRates) {
            addRatingImageView(ContextCompat.getDrawable(context, R.drawable.ic_rate_full))
        }
        if (halfRate == 1) {
            addRatingImageView(ContextCompat.getDrawable(context, R.drawable.ic_rate_half))
        }
        repeat(emptyRates) {
            addRatingImageView(ContextCompat.getDrawable(context, R.drawable.ic_rate_empty))
        }
    }

    private fun addRatingImageView(drawable: Drawable?) {
        val imageView = ImageView(context)
        imageView.setImageDrawable(drawable)
        imageView.setPadding(0, 0, 4.dp, 0)
        addView(imageView)
    }

    private fun Float.roundToHalf(): Float {
        val floor = kotlin.math.floor(this)
        val diff = this - floor
        return when {
            diff <= 0.25 -> floor
            diff <= 0.75 -> floor + 0.5f
            else -> floor + 1f
        }
    }

    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()

    companion object {
        const val numberOfStars = 5
    }
}