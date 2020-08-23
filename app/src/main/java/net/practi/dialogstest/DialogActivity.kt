package net.practi.dialogstest

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.popup_activity.*

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_activity)

        closeWithoutResult.setOnClickListener {
            Log.d("Testing", "Close without result clicked")
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        closeWithResult.setOnClickListener {
            Log.d("Testing", "Close with result clicked")
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}