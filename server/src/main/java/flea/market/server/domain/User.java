package flea.market.server.domain;

import javax.persistence.*;

import flea.market.server.domain.region.Dongs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "USER")
@DynamicUpdate
public class User {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "pwd", nullable = false, length = 50)
    private String pwd;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "nick_name", nullable = false, length = 100)
    private String nickName;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "dong")
    private Dongs dong;

    @Column(name = "restrict_num")
    private Integer restrictNum;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    protected User() {
    }

    @Builder
    public User(String id, String pwd, String userName, String nickName, String phone, String email, Dongs dong, Timestamp createdAt) {
        this.id = id;
        this.pwd = pwd;
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.dong = dong;
        this.status = true;
        this.restrictNum = 0;
        this.createdAt = createdAt;
    }

    public void update(String pwd, String nickName, String phone, String email, Dongs dong, Integer restrictNum) {
        this.pwd = pwd;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.dong = dong;
        this.restrictNum = restrictNum;
    }

    public void delete() {
        this.status = false;
    }

}
