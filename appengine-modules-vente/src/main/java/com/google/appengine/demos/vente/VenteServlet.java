package com.google.appengine.demos.vente;

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

/**
 * Created by Platform-Team-User on 17/03/2017.
 */
public class VenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

        PrintWriter out = resp.getWriter();
        Boolean flag = true;
        int i = 0;
        while (flag) {
            String test = (String) syncCache.get(i);
            if (test == null) {
                flag = false;
            } else {
                out.println(test);
                i++;
            }
        }
    }
}
