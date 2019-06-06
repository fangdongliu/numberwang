package cn.fdongl.numberwangentity.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 255)
    String jobName;

    String description;

    /**
     * 指定输入文件，用逗号分割
     */
    @Lob
    String input;

    String lineFormat;

    String dateFormat;

    /**
     * 指定被当作静态资源的URL后缀，是一个用逗号分割的序列
     */
    String suffix;
    /**
     * Job状态，准备，就绪，等待，执行中，完成
     */
    Long status;

    @CreatedDate
    Date createDate;

    @CreatedBy
    Long createBy;

    @LastModifiedDate
    Date modifyDate;

    @LastModifiedBy
    Long modifiedBy;



}
