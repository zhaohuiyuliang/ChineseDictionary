package com.zi.dian.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class AdapterStrokeOfHanZi extends BaseCAdapter<Integer> {

    private IModelHanZiStroke modelHanZiStroke;
    public AdapterStrokeOfHanZi(Context context, IModelHanZiStroke modelHanZiStroke, List<Integer> integerList) {
        super(context, integerList);
        this.modelHanZiStroke = modelHanZiStroke;
    }

    @Override
    protected int getReLayout() {
        return R.layout.item_stroke;
    }

    @Override
    protected void setData(final Integer localT, HoldView holdView, int position) {
        holdView.setText(R.id.tv_stroke, String.valueOf(localT));
        holdView.setOnClickListener(R.id.tv_stroke, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelHanZiStroke.setOnClickListener(localT);
            }
        });
    }

    public interface IModelHanZiStroke {
        void setOnClickListener(int stroke);
    }

}
