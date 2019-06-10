package cn.fdongl.numberwangentity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TableSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long reportId;

    @Column(name="\"name\"")
    String name;

    String description;

    @Column(name="\"value\"")
    String value;

}
