package app.model.dao;

import app.model.mapping.Custom;
import app.model.mapping.CustomShow;
import common.database.MysqlOrmDriver;
import common.errors.ErrorDefine;
import common.errors.Errors;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.crud.CrudDao;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.page.Order;

// 用户实例的增删改查
public class UserDao {

    private Mango DbDriver;

    public UserDao() {
        this.DbDriver = MysqlOrmDriver.getCrudConnection();
    }

    // 注册用户
    public Errors UserLogin(Custom c) {
        LoginDao dao = this.DbDriver.create(LoginDao.class);
        // 查找数据库中是否有此用户
        Custom cc = dao.findByUserName(c.getcName());
        if (cc == null) {
            dao.add(c);
            return ErrorDefine.Ok;
        } else {
            return ErrorDefine.ErrUserNameExist;
        }
    }

    // 删除用户
    public Errors UserDelete(String uid) {
        LoginDao dao = this.DbDriver.create(LoginDao.class);
        Custom cc = dao.findByUserId(uid);
        if (cc != null) {
            dao.delete(uid);
            return ErrorDefine.Ok;
        } else {
            return ErrorDefine.ErrUserNameExist;
        }
    }

    // 更改用户密码
    public Errors UserUpdatePassword(String uid,String password) {
        LoginDao dao = this.DbDriver.create(LoginDao.class);
        // 用户存在且密码正确则更新
        Custom c = dao.findByCidAndCpassword(uid, password);
        if (c != null) {
            dao.update(c);
            return ErrorDefine.Ok;
        } else {
            return ErrorDefine.ErrUserNotFoundOrPasswordIncorrect;
        }
    }

    @DB(table = Custom.DB_NAME)
    interface LoginDao extends CrudDao<Custom,String> {

        @SQL("select Cid, Cpassword from #table where Cid = :1 and Cpassword = :2")
        Custom findByCidAndCpassword(String cid,String cPassword);

        @SQL("delete from #table where Cid = :1")
        int deleteByCid(String cid);

        @SQL("select Cname from #table where Cname = :1")
        Custom findByUserName(String userName);

        @SQL("select Cid from #table where Cid = :1")
        Custom findByUserId(String uid);
    }

    @DB(table = CustomShow.DB_NAME)
    interface UserInfoDao extends CrudDao<CustomShow,String> {

        @SQL("select Cname, from #table where Cname = :1")
        Custom findByUserName(String userName);
    }
}
