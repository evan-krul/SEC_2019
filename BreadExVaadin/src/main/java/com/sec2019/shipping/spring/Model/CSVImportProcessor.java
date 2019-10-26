package com.sec2019.shipping.spring.Model;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.lang.invoke.DelegatingMethodHandle$Holder;
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

    int dest_ID;
    String dest_name;
    float dest_distance;

    int par_ID;
    float par_arr_time;
    int pdest_ID;
    float par_expiry;
    float par_weight;
    int pDel_Flag;
    int pRef_Flag;
    int ptruck_ID;
    int pLoaded_Flag;
    int pDelivered_Flag;



    public void insertParcelDBRows(){
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
            String arrTime = scan.next();
            String destID = scan.next();
            String expiry = scan.next();
            String weight = scan.next();
            String DelicateFlag = scan.next();
            String RefFlag = scan.next();
            String truckID = scan.next();
            String LoadFlag = scan.next();
            String DeliveredFlag = scan.next();


            if(titleCount != 1){
                //CONVERT STRINGS TO VALUES
                stringToValues(ID, arrTime, destID, expiry, weight, DelicateFlag, RefFlag, truckID, LoadFlag, DeliveredFlag);

                //CONNECT TO THE DATABASE
                Connection conn = null;
                try{
                    conn = DriverManager.getConnection("jdbc:mysql://"+"35.223.182.210"+"/"+"SRPROGRAMMING"+"?useLegacyDatetimeCode=false&serverTimezone=GMT","root","password");

                    //INSERT QUERY TO DATABASE
                    String query = " insert into Parcel(Parcel_ID, Parcel_Arrival_Time, Destination_ID, Parcel_Expiry, Parcel_Weight, Parcel_Delicate_Flag, Parcel_Refrigerate_Flag, Truck_ID, Parcel_IsLoaded_Flag, Parcel_IsDelivered_Flag)"
                            + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    //INSERT TO DATABASE
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, par_ID);
                    preparedStmt.setFloat(2, par_arr_time);
                    preparedStmt.setInt(3, pdest_ID);
                    preparedStmt.setFloat(4, par_expiry);
                    preparedStmt.setFloat(5, par_weight);
                    preparedStmt.setInt(6, pDel_Flag);
                    preparedStmt.setInt(7, pRef_Flag);
                    preparedStmt.setInt(8, ptruck_ID);
                    preparedStmt.setInt(9, pLoaded_Flag);
                    preparedStmt.setInt(10, pDelivered_Flag);

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


    public void insertDestDBRows(){
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
            dest_name = scan.next();
            String Distance = scan.next();

            if(titleCount != 1){
                //CONVERT STRINGS TO VALUES
                stringToValues(ID, Distance);

                //CONNECT TO THE DATABASE
                Connection conn = null;
                try{
                    conn = DriverManager.getConnection("jdbc:mysql://"+"35.223.182.210"+"/"+"SRPROGRAMMING"+"?useLegacyDatetimeCode=false&serverTimezone=GMT","root","password");

                    //INSERT QUERY TO DATABASE
                    String query = " insert into Destination(Destination_ID, Destination_Name, Destination_Distance)"
                            + " values(?, ?, ?)";

                    //INSERT TO DATABASE
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, dest_ID);
                    preparedStmt.setString(2, dest_name);
                    preparedStmt.setFloat(3, dest_distance);

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

    public void stringToValues(String ID, String arrTime, String destID, String expiry, String weight, String DelicateFlag, String RefFlag, String truckID, String LoadFlag, String DeliveredFlag){

        par_ID = Integer.parseInt(ID);
        par_arr_time = Float.parseFloat(arrTime);
        pdest_ID = Integer.parseInt(destID);
        par_expiry = Float.parseFloat(expiry);
        par_weight = Float.parseFloat(weight);
        ptruck_ID = Integer.parseInt(truckID);

        if(DelicateFlag.equals("N")|| DelicateFlag.equals("n")){
            pDel_Flag = 0;
        } else if (DelicateFlag.equals("Y") || DelicateFlag.equals("y")){
            pDel_Flag = 1;
        } else{
            pDel_Flag = 0;
        }

        if(RefFlag.equals("N")|| RefFlag.equals("n")){
            pRef_Flag = 0;
        } else if (RefFlag.equals("Y")|| RefFlag.equals("y")){
            pRef_Flag = 1;
        } else{
            pRef_Flag = 0;
        }

        if(LoadFlag.equals("N")|| LoadFlag.equals("n")){
            pLoaded_Flag = 0;
        } else if (LoadFlag.equals("Y")|| LoadFlag.equals("y")){
            pLoaded_Flag = 1;
        } else{
            pLoaded_Flag = 0;
        }

        if(DeliveredFlag.equals("N")|| DeliveredFlag.equals("n")){
            pDelivered_Flag = 0;
        } else if (DeliveredFlag.equals("Y")|| DeliveredFlag.equals("y")){
            pDelivered_Flag = 1;
        } else{
            pDelivered_Flag = 0;
        }
    }

    public void stringToValues(String ID, String Distance){
        dest_ID = Integer.parseInt(ID);
        dest_distance = Float.parseFloat(Distance);
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
