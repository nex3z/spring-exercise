package com.nex3z.examples.spring.data.transaction;

public interface UserService {

    void insertRecord();

    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback() throws RollbackException;

}
