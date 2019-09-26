package com.cn.service;

/**
 * 数据操作组件
 * UserJPA继承了JpaRepository接口（SpringDataJPA提供的简单数据操作接口）、JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口）、Serializable（序列化接口）。
 * SpringBoot以及SpringDataJPA会为我们全部搞定，SpringDataJPA内部使用了类代理的方式让继承了它接口的子接口都以spring管理的Bean的形式存在。
 */
import com.cn.entity.business.*;
import com.cn.entity.system.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.cn.atomikos.repository.businessdb.*;
import com.cn.atomikos.repository.systemdb.*;
import org.springframework.transaction.annotation.*;

@Service
public class UserService {

    @Autowired
    private SystemJpa systemJpa;
    @Autowired
    private BusinessJpa businessJpa;

    public UserService() {
    }

    @Transactional
    public void insert(){
        UserEntity userEntity=new UserEntity();
        userEntity.setName("黄中华");
        userEntity.setAge(42);
        systemJpa.save(userEntity);

        int n=1/0;

        School school=new School();
        school.setName("连城二中");
        businessJpa.save(school);
    }
}
