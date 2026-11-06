package com.mycompany.modul8.controller;

import com.mycompany.modul8.model.WorkOrder;
import com.mycompany.modul8.model.WorkOrderModel;
import java.sql.SQLException;
import java.util.List;

public class WorkOrderController {

    private final WorkOrderModel workOrderModel;

    public WorkOrderController() {
        this.workOrderModel = new WorkOrderModel();
    }

    public List<WorkOrder> getAllWorkOrders() throws SQLException {
        return workOrderModel.getAllWorkOrders();
    }

    public boolean addWorkOrder(WorkOrder workOrder) throws SQLException {
        return workOrderModel.addWorkOrder(workOrder);
    }

    public boolean updateWorkOrder(WorkOrder workOrder) throws SQLException {
        return workOrderModel.updateWorkOrder(workOrder);
    }

    public boolean deleteWorkOrder(int id) throws SQLException {
        return workOrderModel.deleteWorkOrder(id);
    }
}
