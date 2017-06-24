package demo.hello.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;

import java.util.Date;

@Data
public class BasePOJO {

    @Column("ct")
    private Date createTime;

    @Column("ut")
    private Date updateTime;

}
