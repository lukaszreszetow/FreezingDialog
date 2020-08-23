package net.practi.dialogstest

import android.content.Intent
import android.os.Bundle
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
                startActivity(Intent(this, DialogActivity::class.java))
            }
        })
    }
}
