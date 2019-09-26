package com.cn.entity.business;

/**
 *
 */
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "school")
public class School implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id=null;
    @Column(name = "name")
    private String name=null;

    public School() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
