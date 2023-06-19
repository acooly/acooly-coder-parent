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
import com.acooly.core.utils.Strings;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;

/**
 * Boss界面v4升级v5工具
 *
 * @author zhangpu
 * @date 2023-06-17 23:03
 */
@Slf4j
public class BossView5Upgrader {

    static String searchFormItemTemplate = "            <div class=\"form-group\">\n" +
            "                <label class=\"col-form-label\">${label}：</label>\n" +
            "                ${formItem}\n" +
            "            </div>";

    public static final String LIST_SEARCH_FORM_REGEX = "<form[^>]*?id=\"manage_[\\s\\S]*?_searchform\"[^>]*?>[\\s\\S]*?<\\/form>";

    public static void upgrade(String v4FilePath, String v5FilePath) {

    }

    public static void upgradeList(String v4FileClassPath) {
        Resource resource = new DefaultResourceLoader().getResource(v4FileClassPath);
        try {
            String html = Files.toString(resource.getFile(), StandardCharsets.UTF_8);
            html = doUpgradeListSearchForm(html);
            html = doUpgradeListScript(html);
            log.info("html: \n{}", html);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }


    protected static String doUpgradeListSearchForm(String html) {
        Document doc = Jsoup.parse(html);
        Element tableForm = doc.selectFirst("table.tableForm");
        if (tableForm == null) {
            return html;
        }
        Elements inputs = tableForm.select("input,select");
        Element searchForm = tableForm.parent();
        searchForm.addClass("form-inline ac-form-search");
        String newFormHtml = buildNewSearchForm(inputs);
        searchForm.empty();
        searchForm.append("\n" + newFormHtml);
        doc.outputSettings().prettyPrint(false);
        String searchFormHtml = Parser.unescapeEntities(searchForm.outerHtml(), false);
        searchFormHtml = Strings.replace(searchFormHtml, "<!--/#list-->", "</#list>");
//        log.debug("searchFormHtml: \n{}", searchFormHtml);
        // 正则替换searchForm
        return Strings.replacePattern(html, LIST_SEARCH_FORM_REGEX, Matcher.quoteReplacement(searchFormHtml));
    }

    protected static String doUpgradeListScript(String html) {
        return Strings.replace(html, "$.acooly.framework.registerKeydown", "$.acooly.framework.initPage");
    }

    protected static String doUpgradeListAction(String html) {
        return html;
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

    }

    public static void upgradeShow(String v4FilePath, String v5FilePath) {

    }

    public static void upgradeImport(String v4FilePath, String v5FilePath) {

    }


}
