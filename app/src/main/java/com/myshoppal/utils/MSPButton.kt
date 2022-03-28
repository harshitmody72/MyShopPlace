package com.myshoppal.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 * This class will be used for Custom font text using the Button which inherits the AppCompatButton class.
 */
class MSPButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    /**
     * The init block runs every time the class is instantiated.
     */
    init {
        // Call the function to apply the font to the components.
        applyFont()
    }

    /**
     * Applies a font to a Button.
     */
    private fun applyFont() {

        // This is used to get the file from the assets folder and set it to the title textView.
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)

    }
}