package com.hubin.utildemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hubin.Utils;
import com.hubin.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化  也可以放在 application中
        Utils.init(this,BuildConfig.DEBUG);

        LogUtils.d("onCreate  D : hello!");
        LogUtils.w("onCreate  W : hello!");
        LogUtils.e("onCreate E : hello!");
        LogUtils.i("onCreate I : hello!");
        LogUtils.v("onCreate V : hello!");


    }
}
