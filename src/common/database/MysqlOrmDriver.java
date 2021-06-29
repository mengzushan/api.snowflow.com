package common.database;

import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;

import javax.sql.DataSource;

public class MysqlOrmDriver {

    public static Mango getCrudConnection() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.190.63.74:3306/bank";
        String username = "bank";
        String password = "Cok774..";
        DataSource ds = new DriverManagerDataSource(driverClassName,url,username,password);
        return Mango.newInstance(ds);
    }
}
