package com.zi.dian.ui.fragment;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import dian.zi.com.zidian.R;

/**
 * Created by wangliang on 2017/3/22.
 */

public class FragmentChineseCharacterExplain extends FragmentBase {
    private TextView tv_paraphrase;

    @Override
    public int getResLayout() {
        return R.layout.fragment_chinese_character_explain;
    }

    @Override
    protected void initView() {
        tv_paraphrase = (TextView) view.findViewById(R.id.tv_paraphrase);
        tv_paraphrase.setText(content);

    }
    private String content;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        content = getArguments().getString("content", "没有解释");
    }

    @Override
    public void refreshView(List<?> str) {

    }
}
