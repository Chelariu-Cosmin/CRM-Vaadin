package com.example.application;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import javax.annotation.security.PermitAll;

@Route("login")
@PageTitle("Login | Vaadin CRM")
@PermitAll
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm ();

    public LoginView() {
        addClassName ("login-view");
        setSizeFull ();
        setAlignItems (Alignment.CENTER);
        setJustifyContentMode (JustifyContentMode.CENTER);

        login.setAction ("login");

        add (
                new H1 ("Welcome"),
                login,
                new RouterLink ("Register", RegistrationView.class)
        );

    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                           .getQueryParameters()
                           .getParameters()
                           .containsKey("error")) {
            login.setError(true);
        }
    }
}
