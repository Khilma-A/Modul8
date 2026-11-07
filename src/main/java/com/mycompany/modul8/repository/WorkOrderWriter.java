package com.mycompany.modul8.repository;

import com.mycompany.modul8.model.WorkOrder;
import java.sql.SQLException;

public interface WorkOrderWriter {

    boolean addWorkOrder(WorkOrder workOrder) throws SQLException;

    boolean updateWorkOrder(WorkOrder workOrder) throws SQLException;
}
