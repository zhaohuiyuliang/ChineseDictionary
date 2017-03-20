package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zi.dian.custom.view.MyGridView;
import com.zi.dian.dao.model.ChineseCharacter;
import com.zi.dian.ui.ActivityChineseCharacterBase;
import com.zi.dian.unitl.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/22/16.
 */
public class AdapterHanZiAndStroke extends BaseAdapter {
    private Map<Integer, List<ChineseCharacter>> listMap;
    private List<Integer> keys1;
    private Context context;

    public AdapterHanZiAndStroke(Context context, Map listMap) {
        this.context = context;
        this.listMap = listMap;
        if (listMap != null) {
            keys1 = new ArrayList<>();
            keys1.addAll(listMap.keySet());
            Integer[] integers = keys1.toArray(new Integer[keys1.size()]);
            QuickSort.sortQuick(integers);
            keys1 = Arrays.asList(integers);
        }
    }


    public void setData(Map map) {
        if (map != null) {
            keys1 = new ArrayList<>();
            keys1.addAll(map.keySet());
            Integer[] integers = keys1.toArray(new Integer[keys1.size()]);
            QuickSort.sortQuick(integers);
            keys1 = Arrays.asList(integers);
        }
        listMap = map;
    }

    @Override
    public int getCount() {
        return listMap != null ? listMap.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listMap != null ? listMap.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return listMap != null ? listMap.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView localHoldView;
        if (convertView == null) {
            localHoldView = new HoldView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chinese_character_stroke, null);
            localHoldView.tv_hanzi_stroke = (TextView) convertView.findViewById(R.id.tv_chinese_character_stroke);
            localHoldView.grid_view_zi = (MyGridView) convertView.findViewById(R.id.grid_view_chinese_character);

            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        Integer stroke = keys1.get(position);
        localHoldView.tv_hanzi_stroke.setText("   笔画数：" + stroke);
        localHoldView.adapterHanZi = new AdapterHanZi(context, (ActivityChineseCharacterBase) context, null);
        localHoldView.grid_view_zi.setAdapter(localHoldView.adapterHanZi);
        localHoldView.adapterHanZi.setData(listMap.get(stroke));
        return convertView;
    }


    private class HoldView {
        TextView tv_hanzi_stroke;
        MyGridView grid_view_zi;
        AdapterHanZi adapterHanZi;
    }

}
