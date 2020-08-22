package com.etcenteprise.newsoftheearth.utilities;

import java.text.Normalizer;
import java.util.regex.Pattern;


public class Slug {
    public static String deAccent(String str) {
        String norm = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(norm)
                .replaceAll("")
                .replace(" ", "-")
                .replace(",", "-")
                .replace(";", "")
                .replace("/", "").toLowerCase();
    }
}

























