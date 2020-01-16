package org.flopsar.ext.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;


public class HttpFormatter {

    private static final char SEPARATOR = 0x1E;


    public static String formatService(Object[] args){
        HttpServletRequest req = (HttpServletRequest)args[1];
        return reqresp(req);
    }




    static String reqresp(HttpServletRequest req){
        try {
            /*
				try {
					req.setCharacterEncoding("windows-1250");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
            */

            StringBuilder output = new StringBuilder("URL");
            output.append(SEPARATOR);
            output.append(req.getRequestURL().toString());

            Enumeration hnames = req.getHeaderNames();
            output.append(SEPARATOR);
            output.append("HEADER");
            output.append(SEPARATOR);
            while (hnames.hasMoreElements()){
                String name = (String)hnames.nextElement();
                output.append(name);
                output.append("=");
                output.append(req.getHeader(name));
                output.append("+");
            }

            Enumeration pnames = req.getParameterNames();
            output.append(SEPARATOR);
            output.append("REQPARAMS");
            output.append(SEPARATOR);
            while(pnames.hasMoreElements()){
                String name = (String)pnames.nextElement();
                output.append(name);
                output.append("=");
                output.append(req.getParameter(name));
                output.append("+");
            }

            HttpSession session = req.getSession(false);
            output.append(SEPARATOR);
            output.append("SID");
            output.append(SEPARATOR);

            if (session == null)
                output.append("null");
            else {
                output.append(session.getId());
                output.append(SEPARATOR);
                output.append("ATTRS");
                output.append(SEPARATOR);
                Enumeration snames = session.getAttributeNames();
                while(snames.hasMoreElements()){
                    String name = (String)snames.nextElement();
                    output.append(name);
                    output.append("=");
                    output.append(String.valueOf(session.getAttribute(name)));
                    output.append("+");
                }
            }

            return output.toString();
        } catch (Throwable e) {
            return "EXCEPTION"+ SEPARATOR +e.getMessage();
        }
    }


}
