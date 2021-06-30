package test;

import app.model.dao.UserDao;
import app.model.mapping.Custom;
import common.errors.Errors;
import org.junit.Test;

public class DaoApi_Test {

    @Test
    public void UserDaoTest() {
        UserDao dao = new UserDao();
        Custom c = new Custom();
        c.setcName("xiaohui6");
        c.setCid("6");
        c.setcPassword("cok774");
        c.setEmailAuthState(0);
        c.setPhoneAuthState(0);
        c.setIdentity("333");
        Errors info = dao.UserLogin(c);
        System.out.println(info.getMessage());
        Errors info2 = dao.UserDelete(c.getCid());
        System.out.println(info2.getMessage());
        dao.UserLogin(c);
        Errors info3 = dao.UserUpdatePassword(c.getCid(), c.getcPassword());
        System.out.println(info3.getMessage());
    }
}
