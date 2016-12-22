package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.dao.model.SpellingZi;
import com.zi.dian.unitl.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class AdapterSpellingZiAndStroke extends BaseAdapter {
    private Map<Integer, List<SpellingZi>> listMap;
    private Context context;
    private AdapterSpellingZi.IModelSpellingZi modelSpellingZi;
    private List<Integer> listKey;

    public AdapterSpellingZiAndStroke(Map<Integer, List<SpellingZi>> listMap, Context context, AdapterSpellingZi.IModelSpellingZi modelSpellingZi) {
        this.listMap = listMap;
        this.context = context;
        this.modelSpellingZi = modelSpellingZi;
        if (listMap != null) {
            listKey = new ArrayList<>();
            listKey.addAll(listMap.keySet());
            Integer[] keys = listKey.toArray(new Integer[listKey.size()]);
            QuickSort.sortQuick(keys);
            listKey = Arrays.asList(keys);
        }
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hanzi_stroke, null);
            holdView = new HoldView();
            holdView.tv_stroke = (TextView)convertView.findViewById(R.id.tv_hanzi_stroke);
            holdView.grid_view_zi = (GridView)convertView.findViewById(R.id.grid_view_zi);
            holdView.adapterSpellingZi = new AdapterSpellingZi(context, modelSpellingZi, null);
            holdView.grid_view_zi.setAdapter(holdView.adapterSpellingZi);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        Integer key = listKey.get(position);
        holdView.tv_stroke.setText("笔画数：" + key);
        holdView.adapterSpellingZi.setData(listMap.get(key));
        return convertView;
    }

    public interface IModelSpellingZiStroke {
        void setOnClickListener(SpellingZi spellingZi);
    }

    class HoldView {
        TextView tv_stroke;
        GridView grid_view_zi;
        AdapterSpellingZi adapterSpellingZi;
    }
}
