package com.zi.dian.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/13/16.
 */
public class AdapterRadicalStroke extends BaseCAdapter<String> {
    private IModelRadicalsStroke localModel;


    public AdapterRadicalStroke(Context context, IModelRadicalsStroke model, List<String> list) {
        super(context, list);
        localModel = model;
    }


    @Override
    protected int getReLayout() {
        return R.layout.item_radical_stroke;
    }

    @Override
    protected void setData(final String localT, HoldView holdView, final int position) {
        holdView.setText(R.id.tv_radical_stroke, localT);
        if (localModel != null) {
            holdView.setOnClickListener(R.id.tv_radical_stroke, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setPosition(position);
                    localModel.setOnclickListener("部首笔画数" + localT);
                }
            });
        }
        if (selectPosition == position) {
            holdView.setTextColor(R.id.tv_radical_stroke, R.color.colorRed);
        } else {
            holdView.setTextColor(R.id.tv_radical_stroke, R.color.colorBlack);
        }
    }


    public interface IModelRadicalsStroke {
        void setOnclickListener(String stroke);
    }


}
