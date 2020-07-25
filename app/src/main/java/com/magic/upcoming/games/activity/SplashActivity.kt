package com.magic.upcoming.games.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermission
import com.hjq.permissions.XXPermissions
import com.magic.upcoming.games.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        XXPermissions.with(this)
                .constantRequest()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request(object : OnPermission {
                    override fun noPermission(denied: MutableList<String>?, quick: Boolean) {
                        finish()
                    }

                    @SuppressLint("CheckResult")
                    override fun hasPermission(granted: MutableList<String>?, isAll: Boolean) {
                        onFinish()
                    }
                })
    }

    private fun onFinish(){
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
