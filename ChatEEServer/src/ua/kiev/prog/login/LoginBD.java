package ua.kiev.prog.login;

import java.util.HashMap;
import java.util.Map;

public class LoginBD {

    private static HashMap<String,Login> hm = new HashMap<>();

    public LoginBD() {
        super();
    }

    public HashMap<String, Login> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, Login> hm) {
        this.hm = hm;
    }

    public void addUser(String name, Login login) {
        hm.put(name, login);
    }
    public void addUser(Login login) {
        hm.put(login.getLogin(),login);
    }

    public String allUsres() {
        String users = "";
        for(Map.Entry user : hm.entrySet()){
            Login loginFromBD = (Login) (user.getValue());
            String oneUser;
            oneUser = new String(new StringBuilder().append(user.getKey())
                                                    .append(" --- ")
                                                    .append(online(loginFromBD.isOnline()))
                                                    .append(System.lineSeparator()));
            users = users + oneUser + "";
        }
        return users;
    }


    public boolean checkUser(Login login) {
        for(Map.Entry user : hm.entrySet()){
            if (user.getKey().equals(login.getLogin())) {

                Login loginFromBD = (Login) (user.getValue());
                if (loginFromBD.getPassword().equals(login.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkUserLogin(Login login) {
        for(Map.Entry user : hm.entrySet()){
            if (user.getKey().equals(login.getLogin())) {
                    return true;
            }
        }
        return false;
    }

    public String online(boolean b) {
        if (b) {
            return "online";
        } else {
            return "offline";
        }
    }

}
