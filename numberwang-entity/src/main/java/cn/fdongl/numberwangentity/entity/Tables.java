package cn.fdongl.numberwangentity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long reportId;

    String tableName;

    @Lob
    @Column(name="\"value\"")
    String value;

}
