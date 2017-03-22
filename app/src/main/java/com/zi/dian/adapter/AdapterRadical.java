package com.zi.dian.adapter;

import android.content.Context;
import android.view.View;

import com.zi.dian.dao.model.Radicals;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/16/16.
 */
public class AdapterRadical extends BaseCAdapter<Radicals> {
    private IModelRadicals localModel;

    public AdapterRadical(Context context, List<Radicals> list, IModelRadicals localModel) {
        super(context, list);
        this.localModel = localModel;
    }


    @Override
    protected int getReLayout() {
        return R.layout.item_radicals;
    }

    @Override
    protected void setData(final Radicals localT, HoldView holdView, int position) {
        holdView.setText(R.id.tv_radicals, localT.radical);
        holdView.setOnClickListener(R.id.tv_radicals, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (localModel != null) {
                    localModel.setOnclickListener(localT);
                }
            }
        });
    }

    public interface IModelRadicals {
        void setOnclickListener(Radicals hanZi);
    }

}
