package com.squid.events.commons;

import java.util.Collection;

/**
 * most wanted utils method for Strings
 * @author sergefantino
 *
 */
public class StringUtils {


    public static String listToString(Collection<?> members, String prepend, String delimiter, String append) {
        return listToString(members, prepend, delimiter, append, null);
    }
    
    /**
     * format a list of string into a plain string of the form <prepend>item1<delimiter>item2<delimiter>...<delimiter>itemN<append>
     * @param members
     * @param prepend
     * @param delimiter
     * @param append
     * @return
     */
    public static String listToString(Collection<?> members, String prepend, String delimiter, String append, String quote) {
        StringBuilder s = new StringBuilder();
        boolean first = true;
        s.append(prepend);
        for (Object m : members) {
            if (!first) s.append(delimiter); else first=false;
            if (quote!=null) s.append(quote);
            s.append(m.toString());
            if (quote!=null) s.append(quote);
        }
        s.append(append);
        return s.toString();
    }


    public static String listToString(Object[] members, String prepend, String delimiter, String append) {
        return listToString(members, prepend, delimiter, append, null);
    }
    
    public static String listToString(Object[] members, String prepend, String delimiter, String append, String quote) {
        StringBuilder s = new StringBuilder();
        boolean first = true;
        s.append(prepend);
        for (Object m : members) {
            if (!first) s.append(delimiter); else first=false;
            if (quote!=null) s.append(quote);
            s.append(m);
            if (quote!=null) s.append(quote);
        }
        s.append(append);
        return s.toString();
    }

}
