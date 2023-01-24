package com.example.application.views.list;

import com.example.application.MainLayout;
import com.example.application.data.entity.Contact;
import com.example.application.data.service.CRMService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.vaadin.haijian.Exporter;

import javax.annotation.security.RolesAllowed;

@Route(value = "report", layout = MainLayout.class)
@PageTitle("Report | Vaadin CRM")
@RolesAllowed("ROLE_ADMIN")
public class ReportView extends VerticalLayout {

    private final CRMService service;
    // Data Model
    Grid<Contact> grid = new Grid<> (Contact.class);
    TextField filterText = new TextField ();
    //
    Button saveExcel = new Button ("Save");
    Button saveCSV = new Button ("SaveCSV");

    //
    public ReportView(CRMService service) {
        this.service = service;
        addClassName ("list-view");
        setSizeFull ();

        configureGrid ();
        add (getToolbar (), getContent ());
        updateList ();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout (grid);
        content.setFlexGrow (1, grid);
        content.addClassNames ("content");
        content.setSizeFull ();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames ("contact-grid");
        grid.setSizeFull ();
        grid.setColumns ("firstName", "lastName", "email");
        grid.addColumn (contact -> contact.getStatus ()
                                          .getName ())
            .setHeader ("Status");
        grid.addColumn (contact -> contact.getCompany ()
                                          .getName ())
            .setHeader ("Company");
        grid.getColumns ()
            .forEach (col -> col.setAutoWidth (true));
    }

    private HorizontalLayout getToolbar() {
        //
        filterText.setPlaceholder ("Filter by name...");
        filterText.setClearButtonVisible (true);
        filterText.setValueChangeMode (ValueChangeMode.LAZY);
        filterText.addValueChangeListener (e -> updateList ());
        //
        Anchor anchorXLSX = new Anchor (new StreamResource ("Contacts.xlsx",
                Exporter.exportAsExcel (grid)), "Download As XLSX");
        anchorXLSX.getElement ()
                  .setAttribute ("download", true);

        Anchor anchorCSV = new Anchor (new StreamResource ("Contacts.csv",
                Exporter.exportAsCSV (grid)), "Download As CSV");
        anchorCSV.getElement ()
                 .setAttribute ("download", true);
        //
        HorizontalLayout toolbar = new HorizontalLayout (filterText, anchorXLSX, anchorCSV);
        toolbar.addClassName ("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems (service.findAllContacts (filterText.getValue ()));
    }
}
