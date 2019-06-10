package cn.fdongl.numberwangentity.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long jobId;

    /**
     * 当前记录的版本号，用于判断是否发生了更新
     */
    Long version;

    /**
     * 用分号分割的数组
     */
    @Lob
    String summary;

    /**
     * 用分号分割的数组
     */
    @Lob
    String tables;

    /**
     * 0有效
     * 1完成
     * -1无效
     */

    @Column(name="\"status\"")
    Long status;

}
