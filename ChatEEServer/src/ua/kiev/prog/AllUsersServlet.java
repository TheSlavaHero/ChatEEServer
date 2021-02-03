package ua.kiev.prog;

import ua.kiev.prog.login.LoginBD;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LoginBD bd = new LoginBD();
        OutputStream os = resp.getOutputStream();
        byte[] buf2 = bd.allUsres().getBytes(StandardCharsets.UTF_8);
        os.write(buf2);

    }
}
