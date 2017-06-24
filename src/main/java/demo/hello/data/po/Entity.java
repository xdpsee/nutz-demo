package demo.hello.data.po;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;

import java.util.Date;

@Data
public class Entity {

    @Column("gmt_create")
    private Date createTime;

    @Column("gmt_modified")
    private Date updateTime;

}
