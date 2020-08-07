package com.lyw.customviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyw.lib_customview.recyclerview.BaseCustomViewModel
import com.lyw.lib_customview.universaltextview.LableAttrBean
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val arrays = ArrayList<LableAttrBean>()
//        val lableAttrBean =  LableAttrBean()
//        lableAttrBean.color = R.color.base_colorPrimary
//        lableAttrBean.content = "推"
//        lableAttrBean.rightMargin = 2
//        val lableAttrBean2 =  LableAttrBean()
//        lableAttrBean2.color = R.color.base_colorAccent
//        lableAttrBean2.content = "荐"
//        lableAttrBean2.rightMargin = 2
//        val lableAttrBean3 =  LableAttrBean()
//        lableAttrBean3.color = R.color.color_8BC34A
//        lableAttrBean3.content = "赏罚"
//        lableAttrBean3.rightMargin = 2
//        arrays.add(lableAttrBean)
//        arrays.add(lableAttrBean2)
//        arrays.add(lableAttrBean3)
//        tv_lable.addLables(arrays)
//
//        val lableAttrBean4 =  LableAttrBean()
//        lableAttrBean4.content = "图片加文字"
//        lableAttrBean4.rightMargin = 2
//        tv_lable2.addLable(lableAttrBean4,1)
//
//        tv_lable3.addLable(lableAttrBean4,2)
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
