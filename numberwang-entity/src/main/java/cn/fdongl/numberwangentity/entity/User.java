package cn.fdongl.numberwangentity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true,nullable = false,length = 100)
    String username;

    @Column(nullable = false,length = 100)
    String password;

    @Column(nullable = true,length = 200)
    String mail;

    Date createDate;
}
