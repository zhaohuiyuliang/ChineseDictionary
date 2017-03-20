package com.zi.dian.net;

import android.util.Log;

import com.zi.dian.dao.model.ChineseCharacter;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/15/16.
 */
public class TaskZiParaphrase extends LoadData {
    private String TAG = "TaskZiParaphrase";
    private ChineseCharacter mChineseCharacter;

    public TaskZiParaphrase(ChineseCharacter chineseCharacter) {
        url = chineseCharacter.linkUrl;
        this.mChineseCharacter = chineseCharacter;
    }

    @Override
    public void run() {
        ChineseCharacterParaphrase hanZiParaphrase = parserBS();
        if (getAppliction().getDaoManager().getTableZiParaphrase().isData(hanZiParaphrase)) {

            getAppliction().getDaoManager().getTableZiParaphrase().updateData(hanZiParaphrase);
        } else {
            getAppliction().getDaoManager().getTableZiParaphrase().insertData(hanZiParaphrase);
        }
    }

    @Override
    public void completeLoad(String str) {

    }

    private ChineseCharacterParaphrase parserBS() {
//        List<String> listZiStroke = parserZi();
        ChineseCharacterParaphrase hanZiList = parserZiParaphrase();
        return hanZiList;
    }

    /**
     * @return
     */
    private List<String> parserZi() {
        List<String> pTitleList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 div，即 <div> 标签，并且该标签包含有属性 class 值为"bsbhTitle"
            NodeList divOfTab1 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("class", "on")));
            if (divOfTab1 != null && divOfTab1.size() > 0) {

                for (int i = 0; i < divOfTab1.size(); ++i) {
                    // 获取节点的 Text，即为要获取的汉字笔画
                    NodeList node = divOfTab1.elementAt(i).getChildren();
                    String postTitle = node.elementAt(0).getText();
                    Log.d(TAG, postTitle);
                    pTitleList.add(postTitle);
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return pTitleList;
    }

    private ChineseCharacterParaphrase parserZiParaphrase() {
        ChineseCharacterParaphrase hanZiParaphrase = new ChineseCharacterParaphrase();
        hanZiParaphrase.baseParaphrase = parserZiBaseParaphrase();
        hanZiParaphrase.detailedParaphrase = parserZiDetailedParaphrase();
        hanZiParaphrase.radical = mChineseCharacter.radical;
        hanZiParaphrase.stroke = mChineseCharacter.stroke;
        hanZiParaphrase.zi = mChineseCharacter.zi;
        hanZiParaphrase.spelling = mChineseCharacter.spelling;
        return hanZiParaphrase;
    }

    private String parserZiBaseParaphrase() {
        StringBuffer baseParaphrase = new StringBuffer();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 dd 节点，即 <dd> 标签
            NodeList divOfTab1 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("class", "on")));
            if (divOfTab1 != null && divOfTab1.size() > 0) {

                NodeList nodeList = divOfTab1.elementAt(0).getChildren();
                for (int i = 0; i < nodeList.size(); ++i) {
                    Node node = nodeList.elementAt(i);
                    String str = node.getText();
                    if (str.compareTo("i id=\"note\"") == 0) {
                        break;
                    } else if (!(str.compareTo("br") == 0) && !(str.compareTo("&nbsp;") == 0)) {
                        baseParaphrase.append(str + "\n");
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return baseParaphrase.toString();
    }

    private String parserZiDetailedParaphrase() {
        StringBuffer detailedParaphrase = new StringBuffer();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 dd 节点，即 <dd> 标签
            NodeList divOfTab1 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("class", "off")));
            if (divOfTab1 != null && divOfTab1.size() > 0) {

                NodeList nodeList = divOfTab1.elementAt(0).getChildren();
                for (int i = 0; i < nodeList.size(); ++i) {
                    Node node = nodeList.elementAt(i);
                    String str = node.getText();
                    if (str.compareTo("i id=\"note\"") == 0) {

                        break;
                    } else if (!(str.compareTo("br") == 0) && !(str.compareTo("&nbsp;") == 0)) {
                        detailedParaphrase.append(str + "\n");
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return detailedParaphrase.toString();
    }
}
