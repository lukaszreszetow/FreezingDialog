package net.practi.dialogstest

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class LoadingIndicatorDialog(
    context: Context,
    dialogOwner: WeakReference<LifecycleOwner>
) : LifecycleAwareDialog(context, dialogOwner) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.common_android_loading_indicator_dialog, null)
        setContentView(view)
        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val layoutParams = it.attributes
            layoutParams.width = MATCH_PARENT
            layoutParams.height = MATCH_PARENT
            layoutParams.gravity = Gravity.CENTER
            it.attributes = layoutParams
        }
        setCancelable(false)
    }

    fun setVisibility(isVisible: Boolean) {
        if (isVisible) {
            show()
        } else {
            hide()
        }
    }

    override fun show() {
        logDebugWithObjectRefs("Show called")
        super.show()
    }


    override fun hide() {
        logDebugWithObjectRefs("Hide called")
        super.hide()
    }

    companion object {
        private const val MIN_TIME_SHOWN_TO_HIDE_MILLIS = 500L
        private const val TIME_TO_DELAY_HIDING_MILLIS = 600L
    }
}