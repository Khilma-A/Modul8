package com.mycompany.modul8.repository;

import java.sql.SQLException;

public interface WorkOrderDeleter {

    boolean deleteWorkOrder(int id) throws SQLException;
}
