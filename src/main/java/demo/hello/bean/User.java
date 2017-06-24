package demo.hello.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("t_user")
public class User extends BasePOJO {
    @Id
    private int id;
    @Name
    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String salt;

}
