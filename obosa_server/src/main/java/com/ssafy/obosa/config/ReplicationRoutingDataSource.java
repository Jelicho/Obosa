package com.ssafy.obosa.config;

import com.ssafy.obosa.util.CircularList;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource
{
    private CircularList<String> dataSourceNameList;

    @Override
    protected Object determineCurrentLookupKey()
    {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if(isReadOnly)
        {
            return dataSourceNameList.getOne();
        }
        else
        {
            return "master";
        }
    }
}
