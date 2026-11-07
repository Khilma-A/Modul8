package com.mycompany.modul8.controller;

import com.mycompany.modul8.model.WorkOrder;
import com.mycompany.modul8.model.WorkOrderModel;
import com.mycompany.modul8.repository.WorkOrderReader;
import com.mycompany.modul8.repository.WorkOrderWriter;
import com.mycompany.modul8.repository.WorkOrderDeleter;
import java.sql.SQLException;
import java.util.List;

public class WorkOrderController {

    private final WorkOrderReader reader;
    private final WorkOrderWriter writer;
    private final WorkOrderDeleter deleter;

    public WorkOrderController(
            WorkOrderReader reader,
            WorkOrderWriter writer,
            WorkOrderDeleter deleter) {

        this.reader = reader;
        this.writer = writer;
        this.deleter = deleter;
    }

    public List<WorkOrder> getAllWorkOrders() throws SQLException {
        return reader.getAllWorkOrders();
    }

    public boolean addWorkOrder(WorkOrder workOrder) throws SQLException {
        return writer.addWorkOrder(workOrder);
    }

    public boolean updateWorkOrder(WorkOrder workOrder) throws SQLException {
        return writer.updateWorkOrder(workOrder);
    }

    public boolean deleteWorkOrder(int id) throws SQLException {
        return deleter.deleteWorkOrder(id);
    }
}
