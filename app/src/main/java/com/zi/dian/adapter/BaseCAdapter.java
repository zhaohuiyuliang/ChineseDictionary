package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wangliang on 2017/3/20.
 */

public abstract class BaseCAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> integerList;
    protected int selectPosition = 0;

    public BaseCAdapter(Context context, List<T> integerList) {
        this.context = context;
        this.integerList = integerList;
    }

    public void setData(List<T> list) {
        this.integerList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return integerList != null ? integerList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return integerList != null ? integerList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(getReLayout(), null);
            holdView = new HoldView(convertView);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        T localT = integerList.get(position);
        setData(localT, holdView, position);
        return convertView;
    }

    protected abstract int getReLayout();

    protected abstract void setData(T localT, HoldView holdView, int position);

    protected class HoldView {
        private View convertView;

        public HoldView(View convertView) {
            this.convertView = convertView;
        }

        public void setText(int resId, String content) {
            ((TextView) convertView.findViewById(resId)).setText(content);
        }

        public void setOnClickListener(int resId, View.OnClickListener listener) {
            convertView.findViewById(resId).setOnClickListener(listener);
        }

        public void setTextColor(int resId, int colorId){
            ((TextView) convertView.findViewById(resId)).setTextColor(context.getResources().getColor(colorId));
        }
    }

}
