package com.sec2019.shipping.spring.Model;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

public class CSVImportProcessor {
    private MemoryBuffer buffer;
    public CSVImportProcessor(MemoryBuffer buffer) {
        this.buffer = buffer;
    }

    public void insertDBRows() {
//        TODO
    }
}
