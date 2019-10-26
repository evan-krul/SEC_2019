package com.sec2019.shipping.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "BreadEx Shipping Home", shortName = "BreadExXP")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
// UPLOADS
        H2 csvTitle = new H2("Import CSVs");
        add(csvTitle);

//        TRUCK UPLOAD
        Button buttonCSVTruck = new Button(
                "Upload a new trucks CSV");
        buttonCSVTruck.addClickListener(e ->
                buttonCSVTruck.getUI().ifPresent(ui ->
                        ui.navigate("admin/csvuploadtrk"))
        );
        add(buttonCSVTruck);

//        Dest UPLOAD
        Button buttonCSVDest = new Button(
                "Upload a new destination CSV");
        buttonCSVDest.addClickListener(e ->
                buttonCSVDest.getUI().ifPresent(ui ->
                        ui.navigate("admin/csvuploaddest"))
        );
        add(buttonCSVDest);

        //        Parcel UPLOAD
        Button buttonCSVParcel = new Button(
                "Upload a new parcel CSV");
        buttonCSVParcel.addClickListener(e ->
                buttonCSVParcel.getUI().ifPresent(ui ->
                        ui.navigate("admin/csvuploadpcl"))
        );
        add(buttonCSVParcel);


        // UPLOADS
        H2 ganttTitle = new H2("Shipment Information");
        add(ganttTitle);


        // GANTT Button
        Button buttonGANTT = new Button(
                "View Timeline");
        buttonGANTT.addClickListener(e ->
                buttonGANTT.getUI().ifPresent(ui ->
                        ui.navigate("admin/gantt"))
        );
        add(buttonGANTT);
    }



}
