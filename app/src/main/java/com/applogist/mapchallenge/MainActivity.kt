package com.applogist.mapchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.applogist.mapchallenge.base.BaseActivity
import com.applogist.mapchallenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}