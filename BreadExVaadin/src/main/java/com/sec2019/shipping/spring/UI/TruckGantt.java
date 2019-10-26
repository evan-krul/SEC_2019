package com.sec2019.shipping.spring.UI;

import com.sec2019.shipping.spring.Model.TimeLineItem;
import com.sec2019.shipping.spring.Model.TimeLineModel;
import com.sec2019.shipping.spring.Model.TimeLineParcelItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Designer generated component for the truck-gantt template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("truck-gantt")
@JsModule("./truck-gantt.js")
@Route("admin/gantt")
public class TruckGantt extends PolymerTemplate<TruckGantt.TruckGanttModel> {
    @Id("vaadinVerticalLayout")
    private VerticalLayout vaadinVerticalLayout;
    /**
     * Creates a new TruckGantt.
     */
    public TruckGantt() {
// Back button
com.vaadin.flow.component.button.Button returnButton = new Button("Return");
        returnButton.addClickListener(e ->
                returnButton.getUI().ifPresent(ui ->
                        ui.navigate(""))
        );

        Dialog dialogP = new Dialog();
        dialogP.add(new Label("Would show the parcels if implemented. "));

        dialogP.setWidth("400px");
        dialogP.setHeight("150px");

        List<TimeLineItem> timeLineItemList = TimeLineModel.getTimeLineList();
        Grid<TimeLineItem> grid = new Grid<>(TimeLineItem.class);
        grid.setItems(timeLineItemList);
        grid.addItemClickListener(event -> dialogP.open());

        grid.removeColumnByKey("parcelItems");

// The Grid<>(Person.class) sorts the properties and in order to
// reorder the properties we use the 'setColumns' method.
        grid.setColumns("tripID", "truckID", "truckCapacity", "truckWeight",
                "truckFuelConsumption");


        vaadinVerticalLayout.add(grid);
        vaadinVerticalLayout.add(returnButton);
    }

    /**
     * This model binds properties between TruckGantt and truck-gantt
     */
    public interface TruckGanttModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
