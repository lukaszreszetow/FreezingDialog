package net.practi.dialogstest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {

    lateinit var vm: MainActivityViewModel
    lateinit var loadingDialog: LoadingIndicatorDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        loadingDialog = LoadingIndicatorDialog(this, WeakReference(this))

        startBT.setOnClickListener {
            vm.start()
        }

        vm.isLoadingLive.observe(this, Observer {
            loadingDialog.setVisibility(it)
        })

        vm.startActivity.observe(this, Observer {
            if (it) {
                startActivityForResult(Intent(this, DialogActivity::class.java), CODE)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CODE -> {
                    Log.d("Is Loading visible", "${loadingDialog.isShowing}")
                    loadingDialog.hide()
                }
                else -> {
                    super.onActivityResult(requestCode, resultCode, data)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        const val CODE = 1
    }
}
