package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zi.dian.unitl.QuickSort;

import java.util.Arrays;
import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class AdapterStrokeOfHanZi extends BaseAdapter {
    private Context context;
    private IModelHanZiStroke modelHanZiStroke;
    private List<Integer> integerList;

    public AdapterStrokeOfHanZi(Context context, IModelHanZiStroke modelHanZiStroke, List<Integer> integerList) {
        this.context = context;
        this.modelHanZiStroke = modelHanZiStroke;
        Integer[] integers = integerList.toArray(new Integer[integerList.size()]);
        QuickSort.sortQuick(integers);
        this.integerList = Arrays.asList(integers);
    }

    @Override
    public int getCount() {
        if (integerList != null) {
            return integerList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (integerList != null) {
            return integerList.get(position);
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
            holdView = new HoldView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_stroke, null);
            holdView.tv_stroke = (TextView) convertView.findViewById(R.id.tv_stroke);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        final int stroke = integerList.get(position);
        holdView.tv_stroke.setText(String.valueOf(stroke));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelHanZiStroke.setOnClickListener(stroke);
            }
        });
        return convertView;
    }

    public interface IModelHanZiStroke {
        void setOnClickListener(int stroke);
    }

    class HoldView {
        TextView tv_stroke;
    }
}
