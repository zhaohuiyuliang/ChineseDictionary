package com.zi.dian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zi.dian.dao.model.LetterSpelling;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 6/28/16.
 */
public class AdapterSpelling extends BaseAdapter {
    private List<LetterSpelling> letterSpellingList;
    private Context context;
    private IModelSpelling modelSpelling;

    public AdapterSpelling(List<LetterSpelling> letterSpellingList, Context context, IModelSpelling modelSpelling) {
        this.letterSpellingList = letterSpellingList;
        this.context = context;
        this.modelSpelling = modelSpelling;
    }

    public void setData(List<LetterSpelling> letterSpellingList) {
        this.letterSpellingList = letterSpellingList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return letterSpellingList != null?  letterSpellingList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return letterSpellingList != null ? letterSpellingList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spelling, null);
            holdView = new HoldView();
            holdView.tv_spelling = (TextView) convertView.findViewById(R.id.tv_spelling);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        final LetterSpelling letterSpelling = letterSpellingList.get(position);
        holdView.tv_spelling.setText(letterSpelling.spelling);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelSpelling.setOnclickListener(letterSpelling);
            }
        });
        return convertView;
    }

    public interface IModelSpelling {
        void setOnclickListener(LetterSpelling letterSpelling);
    }

    class HoldView {
        TextView tv_spelling;
    }
}
