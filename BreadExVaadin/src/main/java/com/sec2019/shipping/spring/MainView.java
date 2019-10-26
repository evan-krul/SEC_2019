package com.sec2019.shipping.spring;

import com.vaadin.flow.component.button.Button;
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
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);

        Button buttonCSV = new Button(
                "Navigate to company");
        buttonCSV.addClickListener(e ->
                button.getUI().ifPresent(ui ->
                        ui.navigate("admin/csvupload"))
        );
        add(buttonCSV);

    }



}
