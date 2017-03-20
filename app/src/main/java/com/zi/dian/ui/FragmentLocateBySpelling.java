package com.zi.dian.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.zi.dian.adapter.AdapterLetter;
import com.zi.dian.adapter.AdapterSpelling;
import com.zi.dian.dao.TableLetter;
import com.zi.dian.dao.model.LetterSpelling;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * 按拼音查找汉字
 * Created by wangliang on 6/13/16.
 */
public class FragmentLocateBySpelling extends FragmentBase implements AdapterSpelling.IModelSpelling {
    private ListView list_view_letter_spelling;
    private GridView grid_view_spelling;
    private AdapterLetter mAdapterLetter;
    private AdapterSpelling mAdapterSpelling;

    @Override
    public View loadViewLayout(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.fragment_look_zi_by_radical, viewGroup, false);
        initView();
        return view;
    }

    private void initView() {
        list_view_letter_spelling = (ListView) view.findViewById(R.id.list_view_radical_stroke);
        grid_view_spelling = (GridView) view.findViewById(R.id.grid_view_radicals);
        TableLetter tableLetter = getApplication().getDaoManager().getTableTableLetter();
        List<String> letterSpellings = tableLetter.queryAllLetterData();
        mAdapterLetter = new AdapterLetter(getContext(), letterSpellings, null);
        list_view_letter_spelling.setAdapter(mAdapterLetter);


        List<LetterSpelling> listMap = tableLetter.querySpellingByLetter(letterSpellings.get(0));
        mAdapterSpelling = new AdapterSpelling(listMap, getActivity(), null);
        grid_view_spelling.setAdapter(mAdapterSpelling);
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
