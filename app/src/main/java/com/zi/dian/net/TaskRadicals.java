package com.zi.dian.net;


import com.zi.dian.dao.TablePyRead;
import com.zi.dian.dao.model.PyRead;
import com.zi.dian.dao.model.Radicals;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/13/16.
 */
public class TaskRadicals extends LoadData {

    public TaskRadicals() {
        url = "http://zd.diyifanwen.com/zidian/bs/";
    }


    @Override
    public void run() {
//        List<Radicals> radicalsList = parserRadicals();
//        getAppliction().getDaoManager().getTableBS().batchInsertData(radicalsList);
//        for (Radicals radicals : radicalsList) {
//            new TaskZi(radicals.linkUrl).run();
//        }
//        List<ChineseCharacter> hanZiList = getAppliction().getDaoManager().getTableZi().queryAllData();

//        for (ChineseCharacter hanZi : hanZiList) {
//            new TaskSpellingRead(hanZi.linkUrl).run();
//            new TaskZiParaphrase(hanZi).run();
//        }
        TablePyRead tablePyRead = getAppliction().getDaoManager().getTablePyRead();
        List<PyRead> pyReadList = tablePyRead.queryAllData();
        for (PyRead pyRead : pyReadList) {
            new DownloadMp3(pyRead.tone).run();
        }
//        getAppliction().getFragmentBase().refreshView(radicalsList);
    }

    @Override
    public void completeLoad(final String str) {
        getAppliction().getFragmentBase().refreshView(null);
    }

    private List<Radicals> parserRadicals() {
        List<String> list = parserRadicalsStroke();
        List<Radicals> radicalsList = parserRadicals(list);
        return radicalsList;
    }

    private List<Radicals> parserRadicals(List<String> list) {
        List<Radicals> radicalsList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 dd 节点，即 <dd> 标签
            NodeList divOfTab2 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter[]{(new TagNameFilter("dd"))}));
            if (divOfTab2 != null && divOfTab2.size() > 0) {
                for (int i = 0; i < divOfTab2.size(); ++i) {
                    // 获取指定 dd 标签
                    Node ddNode = divOfTab2.elementAt(i);
                    NodeList ddChildNodeList = ddNode.getChildren();

                    if (ddChildNodeList != null) {
                        //获取指定 dd 标签的子节点中的 <a> 节点
                        Node aNode = ddChildNodeList.elementAt(0);
                        // 在 <a> 节点的子节点中获取 Link 节点
                        Radicals radicals = new Radicals();
                        radicals.radical = ((LinkTag) aNode).getLinkText();
                        radicals.linkUrl = ((LinkTag) aNode).getLink();
                        String attribibute = ((LinkTag) aNode).getAttribute("href");
                        int num = Integer.valueOf(attribibute.substring(2, attribibute.length() - 4));
                        int stroke;
                        if (num < 1000) {
                            stroke = Integer.valueOf(attribibute.substring(2, 3));
                        } else {
                            stroke = Integer.valueOf(attribibute.substring(2, 4));
                        }
                        radicals.stroke = list.get(stroke - 1);
                        radicalsList.add(radicals);
                    }
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return radicalsList;
    }

    private List<String> parserRadicalsStroke() {
        List<String> pTitleList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("GBK");
            // 获取指定的 dt节点，即 <dt> 标签，并且该标签包含有属性 class 值为"bsTitle"
            NodeList divOfTab1 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("dt"), new HasAttributeFilter("class", "bsTitle")));
            if (divOfTab1 != null && divOfTab1.size() > 0) {

                for (int i = 0; i < divOfTab1.size(); ++i) {
                    // 获取节点的 Text，即为要获取的部首笔画
                    NodeList node = divOfTab1.elementAt(i).getChildren();
                    String postTitle = node.elementAt(0).getText();
                    pTitleList.add(postTitle);
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return pTitleList;
    }
}
