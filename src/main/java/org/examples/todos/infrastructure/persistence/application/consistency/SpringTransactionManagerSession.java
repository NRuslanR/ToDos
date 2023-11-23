package org.examples.todos.infrastructure.persistence.application.consistency;

import org.examples.todos.application.common.consistency.BaseSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

/* refactor: circular dependency */
@Component
@RequiredArgsConstructor
public class SpringTransactionManagerSession extends BaseSession
{
    private final PlatformTransactionManager transactionManager;
    private final TransactionDefinition transactionDefinition;

    @Override
    protected void doStart() {

        transactionManager.getTransaction(transactionDefinition);
    }

    @Override
    protected void doCommit() {
        
        var transactionStatus = transactionManager.getTransaction(transactionDefinition);

        transactionManager.commit(transactionStatus);
    }

    @Override
    protected void doRollback() {
        
        var transactionStatus = transactionManager.getTransaction(transactionDefinition);

        transactionManager.rollback(transactionStatus);
    }
    
}
