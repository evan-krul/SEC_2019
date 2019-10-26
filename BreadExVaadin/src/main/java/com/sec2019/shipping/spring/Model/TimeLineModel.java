package com.sec2019.shipping.spring.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimeLineModel {
    public TimeLineModel() {
    }

    public static List<TimeLineItem> getTimeLineList() {
//        GET LIST OF ITEMS
        List<TimeLineItem> timeLineItemList = new ArrayList<>();

        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://"+"35.223.182.210"+"/"+"SRPROGRAMMING"+"?useLegacyDatetimeCode=false&serverTimezone=GMT","root","password");

            //INSERT QUERY TO DATABASE
            String query = "SELECT * FROM SRPROGRAMMING.Trips JOIN SRPROGRAMMING.Truck ON SRPROGRAMMING.Truck.Truck_ID = SRPROGRAMMING.Trips.Truck_ID ;";

            //INSERT TO DATABASE
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            ResultSet rs = preparedStmt.executeQuery();
            while ( rs.next() ) {
                timeLineItemList.add(new TimeLineItem(
                        rs.findColumn("Truck_ID"),
                        rs.findColumn("Truck_Capacity"),
                        0,
                        rs.findColumn("Truck_Fuel_Economy"),
                        rs.findColumn("Destination_ID")
                ));
            }
            conn.close();

        }catch(SQLException ex){
            //ERROR HANDLING
            System.err.println(ex.getMessage());
        }
        return timeLineItemList;
    }



}
