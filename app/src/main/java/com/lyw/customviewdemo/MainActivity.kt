package com.lyw.customviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyw.lib_customview.recyclerview.BaseCustomViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var array = ArrayList<BaseCustomViewModel>()
        for (i in 0..4){
            var baseCustomViewModel = BaseCustomViewModel()
            baseCustomViewModel.type = "test$i"
            array.add(baseCustomViewModel)
        }
        recycleview.setData(array)
        recycleview.setConvert { vh, t -> vh?.setText(R.id.text,t?.type) }
    }
}
