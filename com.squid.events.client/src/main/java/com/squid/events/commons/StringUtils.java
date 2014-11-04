package com.squid.events.commons;

import java.util.Collection;

/**
 * most wanted utils method for Strings
 * @author sergefantino
 *
 */
public class StringUtils {

    /**
     * format a list of string into a plain string of the form <prepend>item1<delimiter>item2<delimiter>...<delimiter>itemN<append>
     * @param members
     * @param prepend
     * @param delimiter
     * @param append
     * @return
     */
    public static String listToString(Collection<String> members, String prepend, String delimiter, String append) {
        StringBuilder s = new StringBuilder();
        s.append(prepend);
        for (String m : members) {
            s.append("'");
            s.append(m);
            s.append("'");
            s.append(delimiter);
        }
        s.replace(s.length() - delimiter.length(), s.length(), "");
        s.append(append);
        return s.toString();
    }

    public static String listToString(String[] members, String prepend, String delimiter, String append) {
        StringBuilder s = new StringBuilder();
        s.append(prepend);
        for (String m : members) {
            s.append("'");
            s.append(m);
            s.append("'");
            s.append(delimiter);
        }
        s.replace(s.length() - delimiter.length(), s.length(), "");
        s.append(append);
        return s.toString();
    }

}
