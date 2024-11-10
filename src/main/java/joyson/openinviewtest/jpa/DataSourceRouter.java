package joyson.openinviewtest.jpa;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        final boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        final String key = readOnly ? DataSourceConstant.READ : DataSourceConstant.WRITE;
        System.out.println("라우팅 DB : "+key);
        return key;
    }
}
