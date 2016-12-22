package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zi.dian.dao.model.HanZi;
import com.zi.dian.dao.model.Radicals;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/16/16.
 */
public class AdapterRadical extends BaseAdapter {
    private List<Radicals> list;
    private Context mContext;
    private IModelRadicals localModel;

    public AdapterRadical(Context context, IModelRadicals model, List<Radicals> list) {
        mContext = context;
        localModel = model;
        this.list = list;
    }

    public void setData(List<Radicals> list) {
        this.list = list;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView localHoldView;
        if (convertView == null) {
            localHoldView = new HoldView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_radicals, null);
            localHoldView.tv_ = (TextView) convertView.findViewById(R.id.tv_radicals);
            localHoldView.image_radicals = (ImageView) convertView.findViewById(R.id.image_radicals);
            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        final Radicals radicals = list.get(position);
        localHoldView.tv_.setText(radicals.radical);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localModel.setOnclickListener(radicals);
            }
        });
        return convertView;
    }

    class HoldView {
        TextView tv_;
        ImageView image_radicals;
    }

    public interface IModelRadicals {
        void setOnclickListener(Radicals hanZi);
    }
}
