package demo.hello;

import demo.hello.bean.User;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Date;

public class MainSetup implements Setup {

    public void init(NutConfig config) {

        Ioc ioc = config.getIoc();
        Dao dao = ioc.get(Dao.class);

        Daos.createTablesInPackage(dao, "demo.hello", false);


        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("123456");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            dao.insert(user);
        }

        ioc.get(NutQuartzCronJobFactory.class);
    }

    public void destroy(NutConfig config) {

    }
}
