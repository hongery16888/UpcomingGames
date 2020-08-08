package com.magic.upcoming.games.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtils {

    public static ArrayList <String> getAllSatisfyStr(String str, String regex) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        ArrayList<String> allSatisfyStr = new ArrayList<>();
        if (regex == null || regex.isEmpty()) {
            allSatisfyStr.add(str);
            return allSatisfyStr;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            allSatisfyStr.add(matcher.group());
        }
        return allSatisfyStr;
    }

    public static String[] returnImageUrlsFromHtml(String content) {
        List <String> imageSrcList = new ArrayList<String>();
        String htmlCode = content;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            //Log.e("huangxiaoguo", "资讯中未匹配到图片链接");
            return null;
        }

        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }
}
