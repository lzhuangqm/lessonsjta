package com.cn.atomikos.repository.systemdb;

/**
 * 数据操作组件
 * UserJPA继承了JpaRepository接口（SpringDataJPA提供的简单数据操作接口）、JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口）、Serializable（序列化接口）。
 * SpringBoot以及SpringDataJPA会为我们全部搞定，SpringDataJPA内部使用了类代理的方式让继承了它接口的子接口都以spring管理的Bean的形式存在。
 */
import java.io.*;
import com.cn.entity.system.*;
import org.springframework.data.jpa.repository.*;

public interface SystemJpa  extends Serializable,JpaRepository<UserEntity,Long>  {

}
