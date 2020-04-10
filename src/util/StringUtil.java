package util;

/**
 * 项目名 BookManager
 * <br>包名 util
 * <br>创建时间 2020/4/10 14:16
 * <br>描述
 *
 * @author Vigilr
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }
}
