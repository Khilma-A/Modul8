package com.mycompany.modul8.repository;

import com.mycompany.modul8.model.WorkOrder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DummyWorkOrderReader implements WorkOrderReader {

    @Override
    public List<WorkOrder> getAllWorkOrders() throws SQLException {
        return new ArrayList<>();
    }
}
