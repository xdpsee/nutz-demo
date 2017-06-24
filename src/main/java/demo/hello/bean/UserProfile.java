package demo.hello.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

@Data
@Table("t_user_profile")
public class UserProfile extends BasePOJO {

    /**关联的用户id*/
    @Id(auto=false)
    @Column("uid")
    protected int userId;
    /**用户昵称*/
    @Column
    protected String nickname;
    /**用户邮箱*/
    @Column
    protected String email;
    /**邮箱是否已经验证过*/
    @Column("email_checked")
    protected boolean emailChecked;
    /**头像的byte数据*/
    @Column
    @JsonField(ignore=true)
    protected byte[] avatar;
    /**性别*/
    @Column
    protected String gender;
    /**自我介绍*/
    @Column("dt")
    protected String description;
    @Column("loc")
    protected String location;
}
