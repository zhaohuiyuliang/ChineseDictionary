package com.zi.dian.net;

import com.zi.dian.dao.model.HanZi;
import com.zi.dian.dao.model.HanZiParaphrase;

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
 * Created by wangliang on 6/23/16.
 */
public class TaskErrorCorrection extends LoadData {
    private HanZi hanZi;
    private HanZiParaphrase hanZiParaphrase;
    private String url;

    public TaskErrorCorrection(HanZi hanZi) {
        this.hanZi = hanZi;
        url = "http://hanyu.baidu.com/zici/s?wd=" + hanZi.zi;
    }

    public TaskErrorCorrection(HanZiParaphrase hanZiParaphrase) {
        this.hanZiParaphrase = hanZiParaphrase;
        url = "http://hanyu.baidu.com/zici/s?wd=" + hanZiParaphrase.zi + "&tupu=01";
//        try {
//            this.url = URLEncoder.encode(url, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public void run() {
        parserZi();
    }

    private List<String> parserZi() {
        List<String> listSpelling = parserDetailMean();
        if (listSpelling == null || listSpelling.size() < 1) {
            listSpelling = parserWordHeader();
        }
        return listSpelling;
    }

    private List<String> parserWordHeader() {
        List<String> hanZiList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("UTF-8");
            // 获取指定的 li 节点，即 <li> 标签
            NodeList divOfTab2 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("id", "word-body")));
            if (divOfTab2 != null && divOfTab2.size() > 0) {
                // 获取指定  标签
                Node divNode = divOfTab2.elementAt(0);
                NodeList divChildNodeList = divNode.getChildren();
                Node pNode = divChildNodeList.elementAt(3);
                NodeList pNodeChildrenList = pNode.getChildren();
                Node pNode3 = pNodeChildrenList.elementAt(3);
                NodeList nodeList3 = pNode3.getChildren();
                Node node4 = nodeList3.elementAt(1);
                NodeList nodeList4 = node4.getChildren();

                Node node5 = nodeList4.elementAt(1);
                NodeList nodeList5 = node5.getChildren();

                Node node6 = nodeList5.elementAt(1);
                NodeList nodeList6 = node6.getChildren();

                Node node7 = nodeList6.elementAt(0);
                NodeList nodeList7 = node7.getChildren();

                Node node8 = nodeList7.elementAt(0);
                NodeList nodeList8 = node8.getChildren();
                Node node9 = nodeList8.elementAt(1);
                LinkTag node10 = (LinkTag) nodeList8.elementAt(3);
                String mp3link = node10.getAttribute("url");
                hanZiList.add(node9.getText());
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return hanZiList;
    }
    private List<String> parserDetailMean() {
        List<String> hanZiList = new ArrayList<>();
        try {
            Parser htmlParser = new Parser(url);
            htmlParser.setEncoding("UTF-8");
            // 获取指定的 li 节点，即 <li> 标签
            NodeList divOfTab2 = htmlParser.extractAllNodesThatMatch(
                    new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("id", "detailmean-wrapper")));
            if (divOfTab2 != null && divOfTab2.size() > 0) {
                // 获取指定  标签
                Node divNode = divOfTab2.elementAt(0);
                NodeList divChildNodeList = divNode.getChildren();
                Node pNode = divChildNodeList.elementAt(5);
                NodeList pNodeChildrenList = pNode.getChildren();

                for (int i = 2; i < pNodeChildrenList.size() - 1; ++i) {
                    //读音
                    Node node1 = pNodeChildrenList.elementAt(i);

                    NodeList nodeList1 = node1.getChildren();
                    Node node2 = nodeList1.elementAt(0);
                    NodeList nodeList2 =   node2.getChildren();
                    Node node3 = nodeList2.elementAt(0);
                    NodeList nodeList3 = node3.getChildren();
                    Node node4 = nodeList3.elementAt(0);
                    hanZiList.add(node4.getText());
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return hanZiList;
    }


    @Override
    public void completeLoad(String str) {

    }
}
