package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.custom.view.MyGridView;
import com.zi.dian.dao.model.HanZi;
import com.zi.dian.ui.ActivityHanZi;
import com.zi.dian.unitl.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/22/16.
 */
public class AdapterHanZiAndStroke extends BaseAdapter {
    private Map<Integer, List<HanZi>> listMap;
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
        if (listMap != null) {
            return listMap.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (listMap != null) {
            return listMap.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (listMap != null) {
            return listMap.size();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView localHoldView;
        if (convertView == null) {
            localHoldView = new HoldView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hanzi_stroke, null);
            localHoldView.tv_hanzi_stroke = (TextView) convertView.findViewById(R.id.tv_hanzi_stroke);
            localHoldView.grid_view_zi = (MyGridView) convertView.findViewById(R.id.grid_view_zi);
            localHoldView.adapterHanZi = new AdapterHanZi(context, (ActivityHanZi) context, null);
            localHoldView.grid_view_zi.setAdapter(localHoldView.adapterHanZi);
            convertView.setTag(localHoldView);
        } else {
            localHoldView = (HoldView) convertView.getTag();
        }
        Integer stroke = keys1.get(position);
        localHoldView.tv_hanzi_stroke.setText("   笔画数：" + stroke);
        localHoldView.adapterHanZi.setData(listMap.get(stroke));
        return convertView;
    }


    class HoldView {
        TextView tv_hanzi_stroke;
        MyGridView grid_view_zi;
        AdapterHanZi adapterHanZi;
    }

}
