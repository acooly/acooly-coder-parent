/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-06-16 16:15
 */
package com.acooly.coder.ftlupgrade;

import com.acooly.core.utils.Strings;
import com.acooly.core.utils.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * @author zhangpu
 * @date 2023-06-16 16:15
 */
@Slf4j
public class JsonpTest {

    String listFile = "classpath:templates/manage/coder/demo1/customer1.ftl";
    String searchFormItemTemplate = "            <div class=\"form-group\">\n" +
            "                <label class=\"col-form-label\">${label}：</label>\n" +
            "                ${formItem}\n" +
            "            </div>";

    @Test
    public void parseSearchForm() throws Exception {
        Resource resource = new DefaultResourceLoader().getResource(listFile);
        Document doc = Jsoup.parse(resource.getFile());
        Element tableForm = doc.selectFirst("table.tableForm");
        if (tableForm == null) {
            return;
        }
        Elements inputs = tableForm.select("input,select");
//        System.out.printf("tableForm: %s\n", tableForm.tagName());

//        if (Collections3.isNotEmpty(inputs)) {
//            inputs.forEach(input -> {
//                Node node = input.previousSibling();
//                String label = Strings.trimToEmpty(node == null ? null : node.toString());
//                label = Strings.removeEnd(label, ":");
//                String onFocus = input.attr("onFocus");
//                boolean isDate = onFocus != null && onFocus.contains("WdatePicker");
//                System.out.printf("%s : %s - %s/%s - %s\n", label, input.attr("name"), input.tagName(), isDate, input.outerHtml());
//            });
//        }

        Element searchForm = tableForm.parent();
        searchForm.addClass("form-inline ac-form-search");
        String newFormHtml = buildNewSearchForm(inputs);
        searchForm.empty();
        searchForm.append("\n" + newFormHtml);
        doc.outputSettings().prettyPrint(false);

        String outputHtml = Parser.unescapeEntities(doc.html(), false);
        outputHtml = Strings.replace(outputHtml, "<!--/#list-->", "</#list>");
        outputHtml = handleListScript(outputHtml);
        // 输出
        Files.write(new File("/Users/zhangpu/workspace/acooly/v5.2/acooly-coder-parent/acooly-coder-platform/acooly-coder-platform-core/src/main/resources/templates/manage/coder/demo1/customer2.ftl"),
                outputHtml, "UTF-8");
    }

    private String buildNewSearchForm(Elements elements) {
        String html = "";
        for (Element e : elements) {
            html = html + buildOneSearchItem(e) + "\n";
        }
        log.info("new search form html: \n{}", html);
        return html;
    }

    private String handleListScript(String html) {
        return Strings.replace(html, "$.acooly.framework.registerKeydown", "$.acooly.framework.initPage");
    }

    private String buildOneSearchItem(Element element) {
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

        // 处理freemarker标签
//        newItemHtml = Strings.replace(newItemHtml, "&lt;", "<");
//        newItemHtml = Strings.replace(newItemHtml, "<!--/#list-->", "</#list>");

//        log.info("newItemHtml: \n{}", newItemHtml);
        return newItemHtml;
    }


    @Test
    public void testJsoupElement() {

        Document document = Jsoup.parse("<input type=\"text\" class=\"text\" size=\"15\" name=\"search_LIKE_username\">");
        Element element = document.body().firstElementChild();
        log.info("tagName: {}", element.tagName());
        log.info("className: {}", element.className());
        log.info("id: {}", element.id());
        element.attributes().forEach(attr -> {
            log.info("attr: {} = {}", attr.getKey(), attr.getValue());
        });
        log.info(element.outerHtml());
    }

}
