package com.cn.atomikos.config;

import javax.transaction.*;
import org.hibernate.engine.transaction.jta.platform.internal.*;

public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    public static TransactionManager transactionManager;
    public static UserTransaction transaction;

    public AtomikosJtaPlatform() {
    }

    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return transaction;
    }
}
