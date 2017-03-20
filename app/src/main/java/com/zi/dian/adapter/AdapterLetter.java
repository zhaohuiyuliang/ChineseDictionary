package com.zi.dian.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 2017/3/20.
 */

public class AdapterLetter extends BaseCAdapter<String> {
    private IModelLetter mIModelLetter;

    public AdapterLetter(Context context, List<String> integerList, IModelLetter mIModelLetter) {
        super(context, integerList);
        this.mIModelLetter = mIModelLetter;
    }

    @Override
    protected int getReLayout() {
        return R.layout.item_radical_stroke;
    }

    @Override
    protected void setData(final String localT, HoldView holdView, int position) {
        holdView.setText(R.id.tv_radical_stroke, String.valueOf(localT));
        holdView.setOnClickListener(R.id.tv_radical_stroke, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIModelLetter.setOnClickListener(localT);
            }
        });
    }

    public interface IModelLetter {
        void setOnClickListener(String letter);
    }
}
