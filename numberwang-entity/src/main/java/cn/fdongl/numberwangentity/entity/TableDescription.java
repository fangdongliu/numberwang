package cn.fdongl.numberwangentity.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TableDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long reportId;

    @Column(name="\"name\"")
    String name;

    @Column(name="\"schema\"")
    String schema;

    String path;

    String description;

    Integer count;

    @Column(name="\"show\"")
    String show;

}
