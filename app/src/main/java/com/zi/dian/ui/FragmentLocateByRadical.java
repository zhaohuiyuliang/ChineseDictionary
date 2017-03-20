package com.zi.dian.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.zi.dian.adapter.AdapterRadical;
import com.zi.dian.adapter.AdapterRadicalStroke;
import com.zi.dian.dao.model.Radicals;

import java.util.ArrayList;
import java.util.List;

import dian.zi.com.zidian.R;

/**
 * 按部首查找汉字
 * Created by wangliang on 6/12/16.
 */
public class FragmentLocateByRadical extends FragmentBase implements AdapterRadicalStroke.IModelRadicalsStroke, AdapterRadical.IModelRadicals {

    private static final short CHINESE_CHARACTER_NUM = 10;
    private static final short COLUMNS_TWO = 2;
    private static final short COLUMNS_ONE = 1;
    private ListView list_view_radical_stroke;
    private GridView grid_view_radicals;
    private AdapterRadicalStroke adapterRadicalStroke;

    private AdapterRadical adapterRadical;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    public View loadViewLayout(LayoutInflater layoutInflater, ViewGroup container) {
        view = layoutInflater.inflate(R.layout.fragment_look_zi_by_radical, container, false);
        initView();
        getApplication().setFragmentBase(this);
        initLoadData();
        return view;
    }

    private void initView() {
        list_view_radical_stroke = (ListView) view.findViewById(R.id.list_view_radical_stroke);
        grid_view_radicals = (GridView) view.findViewById(R.id.grid_view_radicals);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initLoadData() {
        adapterRadicalStroke = new AdapterRadicalStroke(getActivity(), this, null);
        list_view_radical_stroke.setAdapter(adapterRadicalStroke);

        adapterRadical = new AdapterRadical(getActivity(), this, null);

        grid_view_radicals.setAdapter(adapterRadical);


        List<String> listStrokes = new ArrayList<>();
        listStrokes.add("一");
        listStrokes.add("二");
        listStrokes.add("三");
        listStrokes.add("四");
        listStrokes.add("五");
        listStrokes.add("六");
        listStrokes.add("七");
        listStrokes.add("八");
        listStrokes.add("九");
        listStrokes.add("十");
        listStrokes.add("十一");
        listStrokes.add("十二");
        listStrokes.add("十三");
        listStrokes.add("十四");
        listStrokes.add("十五");
        adapterRadicalStroke.setData(listStrokes);
        setOnclickListener("部首笔画数" + listStrokes.get(0));
    }

    @Override
    public void refreshView(List list) {
        Message msg = new Message();
        msg.obj = list;
        handler.sendMessage(msg);

    }

    @Override
    public void setOnclickListener(String stroke) {
        List<Radicals> radicalsList = getApplication().getDaoManager().getTableBS().getDataByStroke(stroke);
        if (radicalsList.size() > CHINESE_CHARACTER_NUM) {
            grid_view_radicals.setNumColumns(COLUMNS_TWO);
        } else {
            grid_view_radicals.setNumColumns(COLUMNS_ONE);
        }
        adapterRadical = new AdapterRadical(getActivity(), this, radicalsList);

        grid_view_radicals.setAdapter(adapterRadical);

    }

    @Override
    public void setOnclickListener(Radicals radicals) {
        Intent intent = new Intent(getActivity(), ActivityChineseCharacterByRadical.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("radicals", radicals);
        getApplication().startActivity(intent);
    }
}
