package cn.fdongl.numberwangentity.entity;

import com.sun.istack.internal.Interned;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
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
     * 2无效
     */
    Long status;

}
