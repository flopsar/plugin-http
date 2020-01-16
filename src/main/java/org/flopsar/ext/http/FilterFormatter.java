package org.flopsar.ext.http;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class FilterFormatter {

    private static final char SEPARATOR = 0x1E;


    public static String formatDoFilter(Object[] args){
        ServletRequest req = (ServletRequest)args[1];
        if (req instanceof HttpServletRequest)
            return HttpFormatter.reqresp((HttpServletRequest)req);

        return "FILTER"+SEPARATOR+req;
    }



}
