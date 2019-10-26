package com.sec2019.shipping.spring;

import com.sec2019.shipping.spring.Model.CSVImportProcessor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.upload.GeneratedVaadinUploadFile;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

import java.io.InputStream;

/**
 * A Designer generated component for the file-upload-ui template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("file-upload-ui")
@JsModule("./file-upload-ui.js")
@Route("admin/csvupload")
public class FileUploadUi extends PolymerTemplate<FileUploadUi.FileUploadUiModel> {


    @Id("vaadinUploadCSV")
    private Upload vaadinUploadCSV;
    private final MemoryBuffer bufferCSV = new MemoryBuffer();


    /**
     * Creates a new FileUploadUi.
     */
    public FileUploadUi() {
        vaadinUploadCSV.setReceiver(bufferCSV);
        vaadinUploadCSV.addSucceededListener(event -> {
            CSVImportProcessor csvImportProcessor = new CSVImportProcessor(bufferCSV);
            csvImportProcessor.insertDBRows();
        });
    }

    /**
     * This model binds properties between FileUploadUi and file-upload-ui
     */
    public interface FileUploadUiModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

}
