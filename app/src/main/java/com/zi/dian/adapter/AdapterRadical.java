package com.zi.dian.adapter;

import android.content.Context;

import com.zi.dian.dao.model.Radicals;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/16/16.
 */
public class AdapterRadical extends BaseCAdapter<Radicals> {

    public AdapterRadical(Context context, List<Radicals> list) {
        super(context, list);
    }


    @Override
    protected int getReLayout() {
        return R.layout.item_radicals;
    }

    @Override
    protected void setData(final Radicals localT, HoldView holdView, int position) {
        holdView.setText(R.id.tv_radicals, localT.radical);
    }
    public interface IModelRadicals {
        void setOnclickListener(Radicals hanZi);
    }

}
