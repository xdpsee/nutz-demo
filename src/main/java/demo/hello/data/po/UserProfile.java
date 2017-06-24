package demo.hello.data.po;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

@Data
@Table("t_user_profiles")
public class UserProfile extends Entity {

    @Id(auto=false)
    @Column("user_id")
    protected int userId;

    @Column
    protected String nickname;

    @Column
    protected String email;

    @Column("email_checked")
    protected boolean emailChecked;

    /**头像的byte数据*/
    @Column
    @JsonField(ignore=true)
    protected byte[] avatar;

    @Column
    protected String gender;

    @Column
    protected String description;

    @Column
    protected String location;
}
