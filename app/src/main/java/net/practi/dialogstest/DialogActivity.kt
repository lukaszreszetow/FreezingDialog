package net.practi.dialogstest

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.popup_activity.*

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_activity)

        closeWithoutResult.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        closeWithResult.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}