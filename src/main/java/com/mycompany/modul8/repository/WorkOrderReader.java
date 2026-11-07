package com.mycompany.modul8.repository;

import com.mycompany.modul8.model.WorkOrder;
import java.sql.SQLException;
import java.util.List;

public interface WorkOrderReader {

    List<WorkOrder> getAllWorkOrders() throws SQLException;
}
