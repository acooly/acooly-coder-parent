/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-03 00:08
 */
package com.acooly.coder.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-05-03 00:08
 */
public class Projects {

    public static void main(String[] args) throws Exception {
        String rootPath = "D:\\workspace\\acooly\\v5\\acooly-coder-parent\\acooly-coder-test";
        System.out.println("ProjectName: " + Projects.getProjectName(rootPath));
        System.out.println("ProjectPath: " + Projects.getProjectPath(rootPath));
    }

    public static String getProjectName(String modulePath) {
        String pomPath = getProjectRootPom(modulePath);
        File pomFile = new File(pomPath);
        String pomProjectName = getProjectNameFormPom(pomFile);

        List<String> subDirs = new ArrayList<>();
        File[] subFiles = pomFile.getParentFile().listFiles();
        for (File subFile : subFiles) {
            if (subFile.isDirectory() && !subFile.isHidden()) {
                subDirs.add(subFile.getName());
            }
        }

        String projectName = pomProjectName;
        if (isProjectName(subDirs,projectName)) {
            return projectName;
        }

        if (StringUtils.contains(projectName, "-")) {
            projectName = StringUtils.substringBeforeLast(projectName, "-");
            if (isProjectName(subDirs,projectName)) {
                return projectName;
            }
        }

        return projectName;
    }

    public static String getProjectPath(String modulePath) {
        File file = new File(modulePath);
        File projectFile = null;
        while (file != null && file.isDirectory()) {
            String[] subFiles = file.list((dir, name) -> name.equals("pom.xml"));
            if (subFiles == null || subFiles.length == 0) {
                break;
            }
            projectFile = file;
            file = file.getParentFile();
        }
        return projectFile.getPath();
    }

    private static boolean isProjectName(List<String> subDirNames, String projectName) {
        int starts = 0;
        for (String dirName : subDirNames) {
            if (StringUtils.startsWith(dirName, projectName)) {
                starts = starts + 1;
            }
        }
        return starts > subDirNames.size() / 2;
    }


    private static String getProjectRootPom(String modulePath) {
        File file = new File(getProjectPath(modulePath),"pom.xml");
        return file.getPath();
    }


    private static String getProjectNameFormPom(File pomFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(pomFile);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath path = xPathFactory.newXPath();
            String artifactId = (String) path.evaluate("project/artifactId", doc, XPathConstants.STRING);
            return artifactId;
        } catch (Exception e) {
            throw new RuntimeException("从Pom文件获取ProjectName失败");
        }
    }

}
