package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/13/16.
 */
public class AdapterRadicalStroke extends BaseAdapter {
    private List<String> list;
    private Context mContext;
    private IModelRadicalsStroke localModel;
    private int selectPosition = 0;

    public AdapterRadicalStroke(Context context, IModelRadicalsStroke model, List<String> list) {
        mContext = context;
        localModel = model;
        this.list = list;
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list != null ? list.get(position) : null;
    }

    public void setPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView localHoldView;
        if (convertView == null) {
            localHoldView = new HoldView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_radical_stroke, null);
            localHoldView.tv_radical_stroke = (TextView) convertView.findViewById(R.id.tv_radical_stroke);
            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        final String stroke = list.get(position);
        localHoldView.tv_radical_stroke.setText(stroke);
        if (localModel != null) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setPosition(position);
                    localModel.setOnclickListener("部首笔画数" + stroke);
                }
            });
        }
        if (selectPosition == position) {
            localHoldView.tv_radical_stroke.setTextColor(mContext.getResources().getColor(R.color.colorRed));
        } else {
            localHoldView.tv_radical_stroke.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }
        return convertView;
    }

    public interface IModelRadicalsStroke {
        void setOnclickListener(String stroke);
    }

    private class HoldView {
        TextView tv_radical_stroke;
    }

}
