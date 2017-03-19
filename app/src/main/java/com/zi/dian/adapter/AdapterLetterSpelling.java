package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zi.dian.dao.model.LetterSpelling;
import com.zi.dian.unitl.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class AdapterLetterSpelling extends BaseAdapter {
    private Context context;
    private Map<String, List<LetterSpelling>> listMap;
    private AdapterSpelling.IModelSpelling modelSpelling;
    private List<String> keys;

    public AdapterLetterSpelling(Context context, Map<String, List<LetterSpelling>> listMap, AdapterSpelling.IModelSpelling modelSpelling) {
        this.context = context;
        this.listMap = listMap;
        this.modelSpelling = modelSpelling;
        if (listMap != null) {
            keys = new ArrayList<>();
            keys.addAll(listMap.keySet());
            String[] keyStr = keys.toArray(new String[keys.size()]);
            QuickSort.sortBy(keyStr);
            keys = Arrays.asList(keyStr);
        }
    }

    @Override
    public int getCount() {
        return listMap != null ? listMap.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listMap != null ? listMap.get(keys.get(position)) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_letter_spelling, null);
            holdView = new HoldView();
            holdView.tv_letter = (TextView) convertView.findViewById(R.id.tv_letter);
            holdView.grid_view_spelling = (GridView) convertView.findViewById(R.id.grid_view_spelling);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        String key = keys.get(position);
        holdView.tv_letter.setText(key);
        holdView.adapterSpelling = new AdapterSpelling(null, context, modelSpelling);
        holdView.grid_view_spelling.setAdapter(holdView.adapterSpelling);
        holdView.adapterSpelling.setData(listMap.get(key));
        return convertView;
    }

    class HoldView {
        TextView tv_letter;
        GridView grid_view_spelling;
        AdapterSpelling adapterSpelling;
    }
}
