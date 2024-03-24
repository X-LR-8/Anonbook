package org.example.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Database;
import org.example.Post;
import org.example.PostUpdate;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private Database database;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        database= Database.getInstance();
        List<Post> list=database.read();
        List<PostUpdate> newlist=new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            Instant instant = Instant.ofEpochMilli(list.get(i).getDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy");
            String date = formatter.format(instant.atZone(ZoneId.systemDefault()));
            PostUpdate postUpdate=new PostUpdate(list.get(i).getId(),date,list.get(i).getText(),list.get(i).getImgurl(),list.get(i).getComments());
            newlist.add(postUpdate);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newlist);
        System.out.println(json);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String comment=req.getParameter("textarea");
        int id=Integer.parseInt(req.getParameter("id"));
        database=Database.getInstance();
        database.update(id,comment);
    }
}
