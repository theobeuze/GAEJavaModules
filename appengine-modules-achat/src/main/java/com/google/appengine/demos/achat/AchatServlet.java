package com.google.appengine.demos.achat;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

public class AchatServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("  <body>");
        out.println("<form action='.' method='post'>");
        out.println("  <div><input type='text' name='nom' /></div>");
        out.println("  <div><input type='submit' value='Save' /></div>");
        out.println("</form>");
        out.println("  </body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        String value = req.getParameter("nom");
        Boolean flag = true;
        int i = 0;
        while (flag) {
            String test = (String) syncCache.get(i);
            if (test == null) {
                syncCache.put(i, value);
                flag = false;
            } else {
                i++;
            }
        }
        doGet(req, resp);
    }
}
