package test;

import app.model.mapping.Custom;
import app.model.mapping.OperRate;
import app.model.mapping.OperRateCustomMapping;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Mapper;
import org.jfaster.mango.crud.CrudDao;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;
import org.junit.Test;

import javax.sql.DataSource;

public class DataBase_Test {

    @Test
    public void OperRateDaoTest() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.190.63.74:3306/bank";
        String username = "bank";
        String password = "Cok774..";
        DataSource ds = new DriverManagerDataSource(driverClassName,url,username,password);
        Mango mango = Mango.newInstance(ds);

        Dao1 dao = mango.create(Dao1.class);
        OperRate or = new OperRate();
        or.setState(0);
        or.setNote("hello");
        or.setSourceId("1");
        or.setDestId("2");
        or.setMountMoney(20.2);
        dao.add(or);
        System.out.println(dao.getOne("1"));
    }

    @DB(table = OperRate.DB_NAME)
    @Mapper(OperRateCustomMapping.class)
    interface Dao1 extends CrudDao<OperRate,String> {}

    @Test
    public void CustomDaoTest() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.190.63.74:3306/bank";
        String username = "bank";
        String password = "Cok774..";
        DataSource ds = new DriverManagerDataSource(driverClassName,url,username,password);
        Mango mango = Mango.newInstance(ds);

        Dao2 dao = mango.create(Dao2.class);
        Custom c = new Custom();
        c.setCid("2");
        c.setcPassword("cok774..");
        c.setIdentity("440782200506141978");
        c.setcName("xiaoli");
        c.setEmailAuthState(0);
        c.setPhoneAuthState(1);
        dao.add(c);
        System.out.println(dao.getOne("2"));
    }

    @DB(table = Custom.DB_NAME)
    interface Dao2 extends CrudDao<Custom,String> {}
}
