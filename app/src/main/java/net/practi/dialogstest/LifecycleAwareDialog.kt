package net.practi.dialogstest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.*
import java.lang.ref.WeakReference

abstract class LifecycleAwareDialog(
    context: Context,
    private val dialogOwner: WeakReference<LifecycleOwner>
) : AppCompatDialog(context), LifecycleOwner {

    private val dialogOwnerName = dialogOwner.get()?.toString()

    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycle

    private val dialogOwnerLifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            logDebugWithObjectRefs("owner ON_DESTROY")
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        logDebugWithObjectRefs("onCreate")
        super.onCreate(savedInstanceState)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        dialogOwner.get()?.apply { attachToOwnerLifecycle(this) }
        logDebugWithObjectRefs("created")
    }

    override fun onStart() {
        logDebugWithObjectRefs("onStart")
        super.onStart()
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        logDebugWithObjectRefs("started")
    }

    override fun onStop() {
        logDebugWithObjectRefs("onStop")
        super.onStop()
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        logDebugWithObjectRefs("stopped")
    }

    override fun dismiss() {
        logDebugWithObjectRefs("dismiss")
        super.dismiss()
        dialogOwner.get()?.apply { detachFromLifecycle(this) }
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        logDebugWithObjectRefs("dismissed")
    }

    private fun attachToOwnerLifecycle(owner: LifecycleOwner) {
        logDebugWithObjectRefs("attachToOwnerLifecycle")
        owner.lifecycle.addObserver(dialogOwnerLifecycleObserver)
    }

    private fun detachFromLifecycle(owner: LifecycleOwner) {
        logDebugWithObjectRefs("detachFromLifecycle")
        owner.lifecycle.removeObserver(dialogOwnerLifecycleObserver)
    }

    protected fun logDebugWithObjectRefs(msg: String) {
        Log.d("LifecycleAwareDialog", "$msg [this: $this, owner: $dialogOwnerName]")
    }
}