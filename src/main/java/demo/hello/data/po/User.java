package demo.hello.data.po;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("t_users")
public class User extends Entity {
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
