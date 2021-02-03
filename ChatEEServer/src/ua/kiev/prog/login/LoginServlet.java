package ua.kiev.prog.login;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class LoginServlet extends HttpServlet {
    public static LoginBD lbd;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        lbd = new LoginBD();
        String msg = "";
        Login lgn = new Login(req.getParameter("login"),req.getParameter("password"));
        String choice = req.getParameter("choice");
        if (choice.equals("2")) {
            if (lbd.checkUser(lgn)) {
                msg = "Success. You are logged in.";
            } else {
                msg = "Incorrect login or password. Try again.";
            }
        } else if(choice.equals("1")) {
            if (!lbd.checkUserLogin(lgn)) {
                lbd.addUser(lgn);
                if (lbd.checkUser(lgn)) {
                    msg = "Registration was successful.";
                }
            } else {
                msg = "Something went wrong, you were not registered. Probably, this username already exists.";
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
            OutputStream os = resp.getOutputStream();
            byte[] buf2 = msg.getBytes(StandardCharsets.UTF_16);
            os.write(buf2);
    }
}



