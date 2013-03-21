package com.riskcare.forums.server.dao;

public class DatabaseServerDialect  extends org.hibernate.dialect.MySQLDialect {
    @Override
    //This method is overriden to force hibernate to pick the declared unique constraint names while table creation. 
    public boolean supportsUniqueConstraintInCreateAlterTable() {
        return false;
    }
}
