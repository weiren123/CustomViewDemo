package com.lyw.lib_customview.recyclerview;

import java.io.Serializable;

/**
 * 应用模块: common
 * <p>
 * 类描述: 用于各个组件之间公用的 契约类,需要共同遵守相关规定
 * <p>
 *
 */
public class BaseCustomViewModel implements Serializable
{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
