package com.mycompany.modul8;

import com.mycompany.modul8.repository.WorkOrderReader;
import com.mycompany.modul8.repository.DummyWorkOrderReader;

public class TestLSP {

    public static void main(String[] args) throws Exception {

        WorkOrderReader reader = new DummyWorkOrderReader();

        System.out.println(reader.getAllWorkOrders());
    }
}
