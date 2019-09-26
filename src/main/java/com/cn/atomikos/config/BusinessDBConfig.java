package com.cn.atomikos.config;

/**
 * 数据源配制
 */
import java.util.*;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.core.env.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.orm.jpa.*;
import com.atomikos.jdbc.*;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.cn.atomikos.repository.businessdb", entityManagerFactoryRef = "businessDBEntityManager", transactionManagerRef = "transactionManager")

public class BusinessDBConfig {

    public BusinessDBConfig() {
    }

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Bean(name = "businessDataSource", initMethod = "init", destroyMethod = "close")
    public AtomikosDataSourceBean systemDataSource() {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl("jdbc:mysql://localhost:3306/oauth");
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword("123456a");
        mysqlXaDataSource.setUser("root");
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        /*AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, "spring.datasource.businessDB.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("businessDB");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);*/
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        xaDataSource.setMinPoolSize(5);
        xaDataSource.setMaxPoolSize(20);
        xaDataSource.setUniqueResourceName("businessDB");
        return xaDataSource;
    }


    @Bean(name = "businessDBEntityManager")
    public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(systemDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.cn.entity.business");
        entityManager.setPersistenceUnitName("businessDBPersistenceUnit");
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.current_session_context_class", "jta");
        //这里指定我们自己创建的AtomikosJtaPlatfom
        properties.put("hibernate.transaction.jta.platform",AtomikosJtaPlatform.class.getName());
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }

    private Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis",
                env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));
        return prop;
    }
}
