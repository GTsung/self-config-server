package com.gt.self.config.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @create: 2022-01-24 11:17
 **/
public class FileScanner {

    public static final String TYPE_CLASS = ".class";
    public static final String TYPE_YML = ".yml";

    public static List<String> findFileByType(String rootPath, List<String> fileList, String fileType) {
        if (fileList == null) {
            fileList = new ArrayList<>();
        }

        File rootFile = new File(rootPath);
        if (!rootFile.isDirectory()) {
            addFile(rootFile.getPath(), fileList, fileType);
        } else {
            String[] subFileList = rootFile.list();
            for (String file : subFileList) {
                String subFilePath = rootPath + "\\" + file;
                File subFile = new File(subFilePath);
                if (!subFile.isDirectory()) {
                    addFile(subFile.getPath(), fileList, fileType);
                } else {
                    findFileByType(subFilePath, fileList, fileType);
                }
            }
        }
        return fileList;
    }

    private static void addFile(String fileName, List<String> fileList, String fileType) {
        if (fileName.endsWith(fileType)) {
            fileList.add(fileName);
        }
    }

    public static String getRealRootPath(String rootPath) {
        if (System.getProperty("os.name").startsWith("Windows")
                && rootPath.startsWith("/")) {
            rootPath = rootPath.substring(1);
            rootPath = rootPath.replaceAll("/", Matcher.quoteReplacement(File.separator));
        }
        return rootPath;
    }

}
