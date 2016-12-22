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
 * Created by wangliang on 6/12/16.
 */
public class AdapterHome extends BaseAdapter {
    private List<String> list;
    private Context mContext;
    private IModelHome localModel;

    public AdapterHome(Context context, IModelHome model, List<String> list) {
        mContext = context;
        localModel = model;
        this.list = list;
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

    private int selectPosition;

    public void setPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_adapter_home, null);
            localHoldView.tv_ = (TextView) convertView.findViewById(R.id.tv_);
            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        final String CONTENT = list.get(position);
        localHoldView.tv_.setText(CONTENT);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localModel.setOnclickListener(position);
            }
        });
        if (selectPosition == position) {
            localHoldView.tv_.setTextColor(mContext.getResources().getColor(R.color.colorSelected));
        } else {
            localHoldView.tv_.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }
        return convertView;
    }

    class HoldView {
        TextView tv_;
    }

    public interface IModelHome {
        void setOnclickListener(int position);
    }
}
