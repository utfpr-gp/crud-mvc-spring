package br.edu.utfpr.crudmvcspring.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static final String INTEGER = "^-?[1-9]\\d*$";

    public static final String NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    public static final String NUMBER = "^([+-]?)\\d*\\.?\\d+$";

    public static final String POSITIVE_NUMBER = "^[1-9]\\d*|0$";

    public static final String NEGATIVE_NUMBER = "^-[1-9]\\d*|0$";

    public static final String FLOAT = "^([+-]?)\\d*\\.\\d+$";

    public static final String POSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

    public static final String NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

    public static final String EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    public static final String COLOR = "^[a-fA-F0-9]{6}$";

    public static final String URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    public static final String IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    public static final String NOT_EMPTY = "^\\S+$";

    public static final String PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

    public static final String RAR = "(.*)\\.(rar|zip|7zip|tgz)$";

    public static boolean is(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isNumber(String str) {
        return is(str, RegexUtil.NUMBER);
    }

    public static boolean isEmail(String str) {
        return is(str, RegexUtil.EMAIL);
    }

    public static boolean isIP(String str) {
        return is(str, RegexUtil.IP4);
    }

    public static boolean isUrl(String str) {
        return is(str, RegexUtil.URL);
    }
}
