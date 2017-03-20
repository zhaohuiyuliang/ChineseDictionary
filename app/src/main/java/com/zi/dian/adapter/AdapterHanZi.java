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
 * Created by wangliang on 6/16/16.
 */
public class AdapterHanZi extends BaseAdapter {
    private List<ChineseCharacter> list;
    private Context mContext;
    private IModelHanZi localModel;

    public AdapterHanZi(Context context, IModelHanZi model, List<ChineseCharacter> list) {
        mContext = context;
        localModel = model;
        this.list = list;
    }

    public void setData(List<ChineseCharacter> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null?list.size(): 0;
    }

    @Override
    public Object getItem(int position) {
        return list != null ?list.get(position): null ;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chinese_character, null);
            localHoldView.tv_ = (TextView) convertView.findViewById(R.id.tv_chinese_character);
            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        final ChineseCharacter chineseCharacter = list.get(position);
        localHoldView.tv_.setText(chineseCharacter.zi);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localModel.setOnclickListener(chineseCharacter);
            }
        });
        if (selectPosition == position) {
            localHoldView.tv_.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        } else {
            localHoldView.tv_.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }
        return convertView;
    }

    class HoldView {
        TextView tv_;
    }

    public interface IModelHanZi {
        void setOnclickListener(ChineseCharacter chineseCharacter);
    }

}
