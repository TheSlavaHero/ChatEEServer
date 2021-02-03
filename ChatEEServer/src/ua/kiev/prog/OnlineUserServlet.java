package ua.kiev.prog;

import ua.kiev.prog.login.Login;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static ua.kiev.prog.login.LoginServlet.lbd;

public class OnlineUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        Login lgn = Login.fromJSON(bufStr);
        lbd.addUser(lgn.getLogin(), lgn);
        for (Map.Entry user : lbd.getHm().entrySet()) {
            Login loginFromBD = (Login) (user.getValue());
            if (System.currentTimeMillis() - loginFromBD.getTime() < 3000) {
                loginFromBD.setOnline(true);
            } else {
                loginFromBD.setOnline(false);
            }
            lbd.addUser(loginFromBD.getLogin(), loginFromBD);
        }


    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
