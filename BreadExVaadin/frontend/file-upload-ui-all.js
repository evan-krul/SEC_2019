import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-upload/src/vaadin-upload.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

class FileUploadUiTrk extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <h2 id="uploadTitle">Upload a CSV File</h2>
 <vaadin-upload id="vaadinUploadCSV"></vaadin-upload>
 <vaadin-button id="vaadinButton">
   Return 
 </vaadin-button>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'file-upload-ui';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(FileUploadUiTrk.is, FileUploadUiTrk);
