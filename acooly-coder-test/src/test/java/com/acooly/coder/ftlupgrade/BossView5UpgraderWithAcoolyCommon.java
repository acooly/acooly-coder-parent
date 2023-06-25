/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-06-17 23:03
 */
package com.acooly.coder.ftlupgrade;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Regexs;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.regex.Matcher;

/**
 * Boss界面v4升级v5工具
 *
 * @author zhangpu
 * @date 2023-06-17 23:03
 */
@Slf4j
public class BossView5UpgraderWithAcoolyCommon {

    static String searchFormItemTemplate = "            <div class=\"form-group\">\n" +
            "                <label class=\"col-form-label\">${label}：</label>\n" +
            "                ${formItem}\n" +
            "            </div>";

    static String searchFormButtonTemplate = "            <div class=\"form-group\">\n" +
            "                <button class=\"btn btn-sm btn-primary\" type=\"button\" onclick=\"${searchFunction}\"><i class=\"fa fa-search fa-fw fa-col\"></i> 查询</button>\n" +
            "            </div>";


    static String editForm2ColRowTemplate = "\t\t\t<div class=\"form-group row\">\n" +
            "\t\t\t\t<label class=\"col-sm-3 col-form-label\">${label}</label>\n" +
            "\t\t\t\t<div class=\"col-sm-9\">\n" +
            "\t\t\t\t\t${content}\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n";

    static String editForm4ColRowTemplate = "\t\t\t<div class=\"form-group row\">\n" +
            "\t\t\t\t<label class=\"col-sm-2 col-form-label\">${label1}</label>\n" +
            "\t\t\t\t<div class=\"col-sm-4\">\n" +
            "\t\t\t\t\t${content1}\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<label class=\"col-sm-2 col-form-label\">${label2}</label>\n" +
            "\t\t\t\t<div class=\"col-sm-4\">\n" +
            "\t\t\t\t\t${content2}\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n";

    public static final String LIST_SEARCH_FORM_REGEX = "<form[^>]*?id=\"manage_[\\s\\S]*?_searchform\"[^>]*?>[\\s\\S]*?<\\/form>";

    public static final String LIST_ROW_ACTION_REGEX = "<div[^>]*?id=\"manage_[\\s\\S]*?_action\"[^>]*?>[\\s\\S]*?<\\/div>";

    public static final String EASYUI_SELECT_REGEX = "<select[^>]*?class=\"easyui-combobox\"[^>]*?>[\\s\\S]*?<\\/select>";

    public static final String EDIT_TABLE_FORM_REGEX = "<table[^>]*?class=\"tableForm\"[^>]*?>[\\s\\S]*?<\\/table>";

    public static void upgrade(String v4FilePath, String v5FilePath) {

    }

    /**
     * 升级v4的List页面
     *
     * @param sourceFileClassPath
     * @param targetFilePath
     */
    public static void upgradeList(String sourceFileClassPath, String targetFilePath) {
        Resource resource = new DefaultResourceLoader().getResource(sourceFileClassPath);
        try {
            String html = Files.readFileToString(resource.getFile(), "UTF-8");
            html = doUpgradeListSearchForm(html);
            html = doUpgradeListScript(html);
            html = doUpgradeListAction(html);
            log.debug("html: \n{}", html);
            File distFile = new File(targetFilePath);
            Files.write(distFile, html, "UTF-8");
        } catch (Exception e) {
            throw new BusinessException("UPGRADE_V4_LIST_ERROR", "升级列表页面失败", e.getMessage());
        }
    }


    /**
     * 升级列表的查询条件表单
     * 升级整个表单的样式和模式，最大程度保持兼容，通过正则替换掉整个表单HTML。主要包括：
     * 1、input表单只做样式升级，删除size属性，保持原有其他属性
     * 2、easyui-combobox转换为select2
     *
     * @param html
     * @return
     */
    protected static String doUpgradeListSearchForm(String html) {
        Document doc = Jsoup.parse(html);
        Element tableForm = doc.selectFirst("table.tableForm");
        if (tableForm == null) {
            return html;
        }
        // 1、解析构建新的查询表单
        Elements inputs = tableForm.select("input,select");
        String newFormHtml = buildNewSearchForm(inputs);
        // 2、解析构建新的查询按钮 a<linkbutton> -> button
        Elements as = tableForm.select("a.easyui-linkbutton");
        Element a = Collections3.getFirst(as);
        String searchButtonHtml = null;
        if (a != null) {
            String onclick = a.attr("onclick");
            searchButtonHtml = searchFormButtonTemplate.replace("${searchFunction}", onclick);
        }

        // 3、清理和添加新的查询表单元素
        Element searchForm = tableForm.parent();
        searchForm.addClass("form-inline ac-form-search");
        searchForm.empty();
        searchForm.append("\n" + newFormHtml);
        if (Strings.isNotBlank(searchButtonHtml)) {
            searchForm.append("\n" + searchButtonHtml + "\n    ");
        }
        // 4、输出设置和补充替换
        doc.outputSettings().prettyPrint(false);
        String searchFormHtml = Parser.unescapeEntities(searchForm.outerHtml(), false);
        searchFormHtml = Strings.replace(searchFormHtml, "<!--/#list-->", "</#list>");

        // 5、正则替换searchForm
        return Strings.replacePattern(html, LIST_SEARCH_FORM_REGEX, Matcher.quoteReplacement(searchFormHtml));
    }

    /**
     * 升级列表的初始化脚本
     * registerKeydown --> initPage
     *
     * @param html list页面的FTL
     * @return 升级后的FTL内容
     */
    protected static String doUpgradeListScript(String html) {
        return Strings.replace(html, "$.acooly.framework.registerKeydown", "$.acooly.framework.initPage");
    }

    /**
     * 升级列表的每行actions按钮
     *
     * @param html
     * @return
     */
    protected static String doUpgradeListAction(String html) {
        String actionDiv = Regexs.finder(LIST_ROW_ACTION_REGEX, html);
        if (Strings.isBlank(actionDiv)) {
            return html;
        }
        // 解析actionDiv内的所有按钮
        Document doc = Jsoup.parse(actionDiv);
        doc.outputSettings().prettyPrint(false);
        Element div = doc.selectFirst("div");
        Elements as = doc.select("a");

        // 转换升级按钮的HTML代码
        Element buttons = new Element("div");
        buttons.addClass("btn-group btn-group-xs");
        for (Element a : as) {
            Element button = new Element("button");
            String onclick = a.attr("onclick");
            button.addClass("btn btn-outline-primary btn-xs")
                    .attr("type", "button")
                    .attr("onclick", onclick);

            // 升级按钮的图标
            if (Strings.contains(onclick, "show")) {
                button.append("<i class=\"fa fa-info fa-fw fa-col\"></i>  查看");
            } else if (Strings.contains(onclick, "edit")) {
                button.append("<i class=\"fa fa-pencil fa-fw fa-col\"></i>  编辑");
            } else if (Strings.contains(onclick, "remove")) {
                button.append("<i class=\"fa fa-trash fa-fw fa-col\"></i>  删除");
            } else {
                String subHtml = a.html();
                String title = a.attr("title");
                if (Strings.isNotBlank(title)) {
                    subHtml = subHtml + "  " + title;
                }
                button.append(subHtml);
            }
            buttons.append("\n        " + button.outerHtml());
        }
        buttons.append("\n      ");
        div.empty();
        div.append("\n      ");
        div.appendChild(buttons);
        div.append("\n    ");
        // 替换输出actionDiv
        String newActionDiv = div.outerHtml();
        return Strings.replacePattern(html, LIST_ROW_ACTION_REGEX, Matcher.quoteReplacement(newActionDiv));
    }

    private static String buildNewSearchForm(Elements elements) {
        String html = "";
        for (Element e : elements) {
            html = html + buildOneSearchItem(e) + "\n";
        }
        return html;
    }

    private static String buildOneSearchItem(Element element) {
        Node node = element.previousSibling();
        String label = Strings.trimToEmpty(node == null ? null : node.toString());
        label = Strings.removeEnd(label, ":");
        label = Strings.removeEnd(label, "：");
        String newItemHtml = searchFormItemTemplate.replace("${label}", label);
        String newFormItemHtml = null;
        if (element.tagName().equals("select")) {
            // select: 从easyUI转换为select2 : easyuiCombobox2select2
            element.removeClass("easyui-combobox").addClass("form-control form-control-sm")
                    .removeAttr("style").removeAttr("editable").removeAttr("panelHeight");
        } else if (element.tagName().equals("input") && element.attr("type").equals("text")) {
            // 文本框：最大兼容处理方式：替换样式即可，其他的属性保留
            element.removeClass("text").addClass("form-control form-control-sm").removeAttr("size");
        }
        newFormItemHtml = element.outerHtml();
        newItemHtml = newItemHtml.replace("${formItem}", newFormItemHtml);
        return newItemHtml;
    }

    public static void upgradeEdit(String v4FilePath, String v5FilePath) {
        Resource resource = new DefaultResourceLoader().getResource(v4FilePath);
        try {
            String html = Files.readFileToString(resource.getFile(), "UTF-8");

            html = doUpgradeEditForm(html);

            log.debug("html: \n{}", html);
            File distFile = new File(v5FilePath);
            Files.write(distFile, html, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("UPGRADE_V4_LIST_ERROR", "升级列表页面失败", e.getMessage());
        }
    }

    protected static String doUpgradeEditForm(String html) {
        Document doc = Jsoup.parse(html);

        Element tableForm = doc.selectFirst("table.tableForm");
        if (tableForm == null) {
            return html;
        }
        // 1、增加form标签新样式
        html = Strings.replace(html, "action=", " class=\"form-horizontal\" action=");

        // 2、解析和转换tableForm内的所有表单
        Element card = new Element("div");
        card.addClass("card-body");
        String cardHtml = "<div class=\"card-body\">\n";
        Elements trs = tableForm.select("tr");
        for (Element tr : trs) {
            Elements tds = tr.select("th,td");
            // 没有td的情况，跳过
            if (tds.size() == 0) {
                continue;
            }
            String rowHtml = "";
            // 常见情况：一行一个表单模式
            if (tds.size() == 2) {
                String label = tds.get(0).html();
                String content = doOneEditFormItem(tds.get(1));
                rowHtml = editForm2ColRowTemplate.replace("${label}", label).replace("${content}", content);
            } else if (tds.size() == 4) {
                // 一行两个表单
                String label1 = tds.get(0).html();
                String content1 = doOneEditFormItem(tds.get(1));
                String label2 = tds.get(2).html();
                String content2 = doOneEditFormItem(tds.get(3));
                rowHtml = editForm4ColRowTemplate.replace("${label1}", label1).replace("${content1}", content1)
                        .replace("${label2}", label2).replace("${content2}", content2);
            } else if (tds.size() == 1) {
                // 单独一行的表单，情况复杂，如果无表单，则为label，否则为表单比如：多媒体框
            }
            cardHtml = cardHtml + rowHtml;
        }
        cardHtml = cardHtml + "\t\t</div>";

        // 3、替换table.tableForm为新的div.card-body
        doc.outputSettings().prettyPrint(false);
        cardHtml = Parser.unescapeEntities(cardHtml, false);
        cardHtml = Strings.replace(cardHtml, "<!--/#list-->", "</#list>");
        html = Strings.replacePattern(html, EDIT_TABLE_FORM_REGEX, Matcher.quoteReplacement(cardHtml));
        return html;
    }

    /**
     * 处理一个表单项
     *
     * @param td
     */
    protected static String doOneEditFormItem(Element td) {
        String content = td.html();
        // 针对easyui文本域的样式进行转换
        content = Strings.replace(content, "class=\"easyui-validatebox\"", "class=\"easyui-validatebox form-control\"");
        content = Strings.replace(content, "class=\"easyui-numberbox\"", "class=\"easyui-numberbox form-control\"");
        // 对select处理: 升级为select2后，通过正则只替换原来的select标签
        Element select = td.selectFirst("select.easyui-combobox");
        if (select != null) {
            select.removeClass("easyui-combobox").addClass("form-control select2bs4")
                    .removeAttr("editable").removeAttr("panelHeight").removeAttr("style")
                    .removeAttr("data-options");
            String newSelectHtml = select.outerHtml();
            log.info("newSelectHtml: {}", newSelectHtml);
            content = Strings.replacePattern(content, EASYUI_SELECT_REGEX, Matcher.quoteReplacement(newSelectHtml));
        }
        return content;
    }


    protected static void doUpgradeEditFormItem(String formItemHtml) {

    }


    public static void upgradeShow(String v4FilePath, String v5FilePath) {

    }

    public static void upgradeImport(String v4FilePath, String v5FilePath) {

    }
}
