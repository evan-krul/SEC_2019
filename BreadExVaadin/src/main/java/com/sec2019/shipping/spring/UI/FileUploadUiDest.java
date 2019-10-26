package com.sec2019.shipping.spring.UI;

import com.sec2019.shipping.spring.Model.CSVImportProcessor;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the file-upload-ui template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("file-upload-ui")
@JsModule("./file-upload-ui-all.js")
@Route("admin/csvuploaddest")
public class FileUploadUiDest extends PolymerTemplate<FileUploadUiDest.FileUploadUiModel> {


    @Id("vaadinUploadCSV")
    private Upload vaadinUploadCSV;
    private final MemoryBuffer bufferCSV = new MemoryBuffer();

    @Id("vaadinButton")
    private Button vaadinButton;

    @Id("uploadTitle")
    private H2 uploadTitle;


    /**
     * Creates a new FileUploadUi.
     */
    public FileUploadUiDest() {
        uploadTitle.setText("Upload a Destination CSV File");

        // File upload manager

        vaadinUploadCSV.setReceiver(bufferCSV);
        Notification notification = new Notification(
                "CSV Uploaded", 3000,
                Notification.Position.TOP_START);
        vaadinUploadCSV.addSucceededListener(event -> {
            CSVImportProcessor csvImportProcessor = new CSVImportProcessor(bufferCSV);
            notification.open();
            csvImportProcessor.insertDestDBRows();
        });

        vaadinButton.addClickListener(e ->
                vaadinButton.getUI().ifPresent(ui ->
                        ui.navigate(""))
        );
    }

    /**
     * This model binds properties between FileUploadUi and file-upload-ui
     */
    public interface FileUploadUiModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

}
