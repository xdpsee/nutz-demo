package demo.hello;


import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
        "*anno", "demo.hello",
        "*tx", // 事务拦截 aop
        "*quartz"})
@Modules
@ChainBy(args = "mvc/chain.js")
@SetupBy(value = MainSetup.class)
public class MainModule {

    @At("/hello")
    @Ok("jsp:jsp.hello")
    public String hello() {
        return "Hello";
    }

}


