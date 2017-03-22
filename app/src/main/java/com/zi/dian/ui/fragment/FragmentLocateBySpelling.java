package com.zi.dian.ui.fragment;

import android.content.Intent;
import android.widget.GridView;
import android.widget.ListView;

import com.zi.dian.adapter.AdapterLetter;
import com.zi.dian.adapter.AdapterSpelling;
import com.zi.dian.dao.TableLetter;
import com.zi.dian.dao.model.LetterSpelling;
import com.zi.dian.ui.ActivitySpellingZiStroke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dian.zi.com.zidian.R;

/**
 * 按拼音查找汉字
 * Created by wangliang on 6/13/16.
 */
public class FragmentLocateBySpelling extends FragmentBase implements AdapterSpelling.IModelSpelling ,AdapterLetter.IModelLetter {
    private ListView list_view_letter_spelling;
    private GridView grid_view_spelling;
    private AdapterLetter mAdapterLetter;
    private AdapterSpelling mAdapterSpelling;


    @Override
    public int getResLayout() {
        return R.layout.fragment_look_zi_by_radical;
    }
    private TableLetter tableLetter;
    private Map<String, List<LetterSpelling>> mStringListMap;
    @Override
    protected void initView() {
        mStringListMap = new HashMap<>();
        list_view_letter_spelling = (ListView) view.findViewById(R.id.list_view_radical_stroke);
        grid_view_spelling = (GridView) view.findViewById(R.id.grid_view_radicals);
        tableLetter = getApplication().getDaoManager().getTableTableLetter();
        List<String> letterSpellings = tableLetter.queryAllLetterData();
        mAdapterLetter = new AdapterLetter(getContext(), letterSpellings, this);
        list_view_letter_spelling.setAdapter(mAdapterLetter);


        setOnClickListener(letterSpellings.get(0));
    }

    @Override
    public void refreshView(List str) {

    }

    @Override
    public void setOnClickListener(String letter) {
        List<LetterSpelling> listMap = mStringListMap.get(letter);
        if (listMap == null) {
            listMap = tableLetter.querySpellingByLetter(letter);
        }
        mAdapterSpelling = new AdapterSpelling(listMap, getActivity(), this);
        grid_view_spelling.setAdapter(mAdapterSpelling);
    }

    @Override
    public void setOnclickListener(LetterSpelling letterSpelling) {
        Intent intent = new Intent(getActivity(), ActivitySpellingZiStroke.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("spelling", letterSpelling.spelling);
        getApplication().startActivity(intent);
    }
}
