package com.example.application;

import com.example.application.Register;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Register | Vaadin CRM")
@Route("register")
@PermitAll
public class RegistrationView extends VerticalLayout {



    public RegistrationView() {
        Register registrationForm = new Register();
        addClassName ("register-view");
        setSizeFull ();
        setAlignItems (Alignment.CENTER);
        setJustifyContentMode (JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);

        add(
                new H1 ("Hi, Glad to see you!"),
                registrationForm
        );
    }
}
