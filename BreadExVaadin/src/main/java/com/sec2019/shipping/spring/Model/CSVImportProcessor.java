package com.sec2019.shipping.spring.Model;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CSVImportProcessor {
    private MemoryBuffer buffer;
    public CSVImportProcessor(MemoryBuffer buffer) {
        this.buffer = buffer;
    }

    int truck_ID;
    float truck_Distance;
    float truck_Speed;
    float truck_Capacity;
    float truck_Fuel;
    int truck_Delicate;
    int truck_Fridge;


    public void insertTruckDBRows() {
        //READ IN CSV
        BufferedReader input = new BufferedReader(new InputStreamReader(buffer.getInputStream()));
        String line = "";
        int titleCount = 0;
        while(true){
            try {
                if (!((line = input.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            //PARSE THE DATA
            titleCount++;
            Scanner scan = new Scanner(line).useDelimiter(",");
            String ID = scan.next();
            String Distance = scan.next();
            String Speed = scan.next();
            String Capacity = scan.next();
            String Fuel = scan.next();
            String Delicate = scan.next();
            String Fridge = scan.next();

            if(titleCount != 1){
                //CONVERT STRINGS TO VALUES
                stringToValues(ID, Distance, Speed, Capacity, Fuel, Delicate, Fridge);

                //TEST PARSE
//                System.out.print(truck_ID + " ");
//                System.out.print(truck_Distance + " ");
//                System.out.print(truck_Speed + " ");
//                System.out.print(truck_Capacity + " ");
//                System.out.print(truck_Fuel + " ");
//                System.out.print(truck_Delicate + " ");
//                System.out.println(truck_Fridge);

                //CONNECT TO THE DATABASE
                Connection conn = null;
                try{
                    conn = DriverManager.getConnection("jdbc:mysql://"+"35.223.182.210"+"/"+"SRPROGRAMMING"+"?useLegacyDatetimeCode=false&serverTimezone=GMT","root","password");

                    //INSERT QUERY TO DATABASE
                    String query = " insert into Truck(Truck_ID, Truck_Initial_Distance, Truck_Speed, Truck_Capacity, Truck_Fuel_Economy, Truck_Delicate_Flag, Truck_Refrigerate_Flag)"
                            + " values(?, ?, ?, ?, ?, ?, ?)";

                    //INSERT TO DATABASE
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, truck_ID);
                    preparedStmt.setFloat(2, truck_Distance);
                    preparedStmt.setFloat(3, truck_Speed);
                    preparedStmt.setFloat(4, truck_Capacity);
                    preparedStmt.setFloat(5, truck_Fuel);
                    preparedStmt.setInt(6, truck_Delicate);
                    preparedStmt.setInt(7, truck_Fridge);

                    preparedStmt.execute();

                    conn.close();

                }catch(SQLException ex){
                    //ERROR HANDLING
                    System.err.println(ex.getMessage());
                }
            }
        }
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stringToValues(String ID, String Distance, String Speed, String Capacity, String Fuel, String Delicate, String Fridge){
        truck_ID = Integer.parseInt(ID);
        truck_Distance = Float.parseFloat(Distance);
        truck_Speed = Float.parseFloat(Speed);
        truck_Capacity = Float.parseFloat(Capacity);
        truck_Fuel = Float.parseFloat(Fuel);

        if(Delicate.equals("N")|| Delicate.equals("n")){
            truck_Delicate = 0;
        } else if (Delicate.equals("Y") || Delicate.equals("y")){
            truck_Delicate = 1;
        } else{
            truck_Delicate = 0;
        }

        if(Fridge.equals("N")|| Fridge.equals("n")){
            truck_Fridge = 0;
        } else if (Fridge.equals("Y")|| Fridge.equals("y")){
            truck_Fridge = 1;
        } else{
            truck_Fridge = 0;
        }
    }
}
