package com.zi.dian.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zi.dian.adapter.AdapterLetterSpelling;
import com.zi.dian.adapter.AdapterSpelling;
import com.zi.dian.dao.TableLetter;
import com.zi.dian.dao.model.LetterSpelling;

import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

/**
 * 按拼音查找汉字
 * Created by wangliang on 6/13/16.
 */
public class FragmentLocateBySpelling extends FragmentBase implements AdapterSpelling.IModelSpelling {
    private ListView list_view_letter_spelling;
    private AdapterLetterSpelling adapterLetterSpelling;

    @Override
    public View loadViewLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_look_zi_by_spelling, viewGroup, false);
        initView();
        return view;
    }

    private void initView() {
        list_view_letter_spelling = (ListView) view.findViewById(R.id.list_view_letter_spelling);
        TableLetter tableLetter = getApplication().getDaoManager().getTableTableLetter();
        Map<String, List<LetterSpelling>> listMap = tableLetter.queryAllData();
        adapterLetterSpelling = new AdapterLetterSpelling(getActivity(), listMap, this);
        list_view_letter_spelling.setAdapter(adapterLetterSpelling);
    }

    @Override
    public void refreshView(List str) {

    }

    @Override
    public void setOnclickListener(LetterSpelling letterSpelling) {
        Intent intent = new Intent(getActivity(), ActivitySpellingZiStroke.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("spelling", letterSpelling.spelling);
        getApplication().startActivity(intent);
    }
}
