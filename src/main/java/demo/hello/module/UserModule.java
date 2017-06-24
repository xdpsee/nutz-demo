package demo.hello.module;

import demo.hello.data.po.User;
import demo.hello.data.po.UserProfile;
import demo.hello.utils.UserUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;
import java.util.Date;

@IocBean
@At("/user")
@Ok("json:{locked:'password|salt',ignoreNull:true}")
@Fail("http:500")
@Filters(@By(type=CheckSession.class, args={"me", "/"}))
public class UserModule {

    @Inject
    protected Dao dao;

    @At("/")
    @Ok("jsp:jsp.user.list")
    public void list() {
    }

    @At
    @Filters()
    public Object login(@Param("username") String username, @Param("password") String password, HttpSession session) {

        final User user = dao.fetch(User.class, Cnd.where("name", "=", username)
                .and("password", "=", password));
        if (user == null) {
            return false;
        } else {
            session.setAttribute("me", user.getId());
        }

        return true;
    }

    @At
    @Ok(">>:/")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @At
    public Object add(@Param("..") User user) {

        final NutMap result = new NutMap();
        final String message = UserUtils.check(dao, user, true);
        if (message != null) {
            return result.setv("ok", false).setv("msg", message);
        }

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user = dao.insert(user);

        return result.setv("ok", true).setv("data", user);
    }

    @At
    public Object update(@Param("..") User user) {

        final NutMap result = new NutMap();
        final String message = UserUtils.check(dao, user, false);
        if (message != null) {
            return result.setv("ok", false).setv("msg", message);
        }

        user.setName(null);
        user.setCreateTime(null);
        user.setUpdateTime(new Date());
        int rows = dao.updateIgnoreNull(user);

        return result.setv("ok", rows > 0);
    }

    @At
    @Aop(TransAop.READ_COMMITTED)
    public Object delete(@Param("id") int id, @Attr("me") int me) {

        if (me == id) {
            return new NutMap().setv("ok", false).setv("msg", "不能删除当前用户!!");
        }
        dao.delete(User.class, id); // 再严谨一些的话,需要判断是否为>0
        dao.clear(UserProfile.class, Cnd.where("userId", "=", me));

        return new NutMap().setv("ok", true);
    }

    @At
    public Object query(@Param("name") String name, @Param("..")Pager pager) {

        Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "like", "%" + name + "%");

        QueryResult result = new QueryResult();
        result.setList(dao.query(User.class, cnd, pager));
        pager.setRecordCount(dao.count(User.class));
        result.setPager(pager);

        return result;
    }
}


