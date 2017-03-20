package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zi.dian.dao.model.ChineseCharacter;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/13/16.
 */
public class AdapterContent extends BaseAdapter {
    private List list;
    private Context mContext;
    private IModelContent modelContent;

    public AdapterContent(Context context, List list, IModelContent modelContent) {
        mContext = context;
        this.list = list;
        this.modelContent = modelContent;
    }

    public void setData(List list) {
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


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HoldView localHoldView;
        if (convertView == null) {
            localHoldView = new HoldView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_content, null);
            localHoldView.tv_hanzi = (TextView) convertView.findViewById(R.id.tv_chinese_character);
            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        final ChineseCharacter useZi = (ChineseCharacter) list.get(position);
        localHoldView.tv_hanzi.setText(useZi.zi);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelContent.setOnclickListener(useZi.zi);
            }
        });
        return convertView;
    }

    public interface IModelContent {
        void setOnclickListener(String zi);
    }

    class HoldView {
        TextView tv_hanzi;
    }

}
