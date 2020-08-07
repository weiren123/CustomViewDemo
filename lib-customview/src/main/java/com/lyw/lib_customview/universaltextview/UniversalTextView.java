package com.lyw.lib_customview.universaltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.lyw.lib_customview.R;
import com.lyw.lib_customview.utils.DensityUtil;

import java.util.ArrayList;

public class UniversalTextView extends AppCompatTextView {
    private Context context;
    private  GradientDrawable drawable;
    public static final int TEXT_TYPE = 0;
    public static final int IMG_TYPE = 1;
    public static final int IMG_TEXT_TYPE = 2;
    private int labelBg;

    public UniversalTextView(Context context) {
        this(context,null);
    }

    public UniversalTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UniversalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        drawable = new GradientDrawable();
        initView(context,attrs);
    }
    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UniversalTextView);
        labelBg = typedArray.getResourceId(R.styleable.UniversalTextView_labe_backgroud, R.color.base_white);
        float cornerRadius = typedArray.getDimension(R.styleable.UniversalTextView_labeRadius, 2);
        float labeType = typedArray.getInt(R.styleable.UniversalTextView_labeType, 0);
        boolean isAddLable = typedArray.getBoolean(R.styleable.UniversalTextView_addLable, false);
        String labeText = typedArray.getString(R.styleable.UniversalTextView_labeText);
        int lableBg = typedArray.getResourceId(R.styleable.UniversalTextView_lableBg,R.color.base_white);
        int lableShowType = typedArray.getInt(R.styleable.UniversalTextView_lableShowType,0);
        int lableRightmargin = typedArray.getInt(R.styleable.UniversalTextView_lableRightmargin,0);
        if(labeType==0){
            drawable.setColor(context.getResources().getColor(labelBg));
        }else {
            drawable.setStroke(2,getResources().getColor(labelBg));
        }
        drawable.setCornerRadius(DensityUtil.dip2px(context, cornerRadius));
        this.setBackground(drawable);

        if(isAddLable){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" ");
            IconTextSpan topSpan = null;
            if(TEXT_TYPE==lableShowType){
                topSpan = new IconTextSpan(context, lableBg, labeText);
            }else {
                if(lableShowType==IMG_TYPE){
                    topSpan = new IconTextSpan(context,R.color.base_white,labeText,IMG_TYPE);
                }else {
                    topSpan = new IconTextSpan(context,R.color.base_white,labeText,IMG_TEXT_TYPE);
                }
            }
            topSpan.setRightMarginDpValue(lableRightmargin);
            stringBuilder.append(getText());
            SpannableString spannableString =new SpannableString(stringBuilder.toString());
            spannableString.setSpan(topSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(spannableString);
        }
    }
    public void addLable(LableAttrBean lableAttrBean,int type){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        IconTextSpan topSpan = null;
        if(TEXT_TYPE==type){
            topSpan = new IconTextSpan(context, lableAttrBean.getColor(), lableAttrBean.getContent());
        }else {
            if(type==IMG_TYPE){
                topSpan = new IconTextSpan(context,R.color.base_white,lableAttrBean.getContent(),IMG_TYPE);
            }else {
                topSpan = new IconTextSpan(context,R.color.base_white,lableAttrBean.getContent(),IMG_TEXT_TYPE);
            }
        }
        topSpan.setRightMarginDpValue(lableAttrBean.getRightMargin());
        stringBuilder.append(getResources().getString(R.string.content));
        SpannableString spannableString =new SpannableString(stringBuilder.toString());
        spannableString.setSpan(topSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(spannableString);
    }

    public void addLables(ArrayList<LableAttrBean> lableAttrList){
        StringBuilder  stringBuilder = new StringBuilder();
        ArrayList<IconTextSpan> iconTextSpans = new ArrayList<>();
        for (int i = 0; i < lableAttrList.size(); i++) {
            stringBuilder.append(",");
            IconTextSpan topSpan = new IconTextSpan(context, lableAttrList.get(i).getColor(), lableAttrList.get(i).getContent());
            topSpan.setRightMarginDpValue(lableAttrList.get(i).getRightMargin());
            iconTextSpans.add(topSpan);
        }
        stringBuilder.append(getResources().getString(R.string.content));
        SpannableString spannableString =new SpannableString(stringBuilder.toString());
        for (int i = 0; i < iconTextSpans.size(); i++) {
            spannableString.setSpan(iconTextSpans.get(i), i, i+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(spannableString);
    }
}
