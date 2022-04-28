package com.mapchallenge

import com.mapchallenge.base.BaseActivity
import com.mapchallenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}