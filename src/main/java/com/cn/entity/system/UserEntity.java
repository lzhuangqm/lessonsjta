package com.cn.entity.system;

/**
 *
 */
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "userLog")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id=null;
    @Column(name = "name")
    private String name=null;
    @Column(name = "age")
    private int age=0;
    @Column(name = "address")
    private String address=null;
    @Column(name = "pwd")
    private String pwd=null;

    public UserEntity() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
