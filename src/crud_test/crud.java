package crud_test;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;

import javax.sql.DataSource;

public class crud {
    public static void main() {
        String driverClassName = "com.mysql.jdbc.driver";
        String url = "jdbc:mysql://10.190.12.21:3306/mango_example";
        String username = "root";
        String password = "Cok774..";
        DataSource ds = new DriverManagerDataSource(driverClassName, url, username, password);
        Mango mango = Mango.newInstance(ds);
        // 调用dao并插入数据
        FruitDao dao = mango.create(FruitDao.class);
        String name = "apple";
        int num = 7;
        dao.add(name,num);
        System.out.println(dao.getTotalNum(name));
    }
    @DB
    interface FruitDao {

        // 插入数据
        @SQL("insert into fruit(name,num) values(:1,:2)")
        public void add(String name, int num);

        // 根据name取num的总和
        @SQL("select sum(num) from fruit where name=:1")
        public int getTotalNum(String name);
    }
}
