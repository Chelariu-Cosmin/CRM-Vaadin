package com.example.application;

import com.example.application.security.SecurityService;
import com.example.application.views.list.DashboardView;
import com.example.application.views.list.ListView;
import com.example.application.views.list.ReportView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService=securityService;
        createHeader ();
        createDrawer ();
    }

    private void createDrawer() {
        RouterLink listView = new RouterLink ("List", ListView.class);
        listView.setHighlightCondition (HighlightConditions.sameLocation ());

        addToDrawer (new VerticalLayout (
                listView,
                new RouterLink ("Dashboard", DashboardView.class),
                new RouterLink ("Report", ReportView.class)
        ));
    }

    private void createHeader() {
        H1 logo = new H1 ("Vaadin CRM");
        logo.addClassNames ("text-l", "m-m");

        Avatar avatar = new Avatar("Chelariu Cosmin");
        Button actionButton = new Button("enable/disable", event -> {
            avatar.setTooltipEnabled (!avatar.isTooltipEnabled ());
        });

        Button logOut = new Button ("Log out",e -> securityService.logout () );
        HorizontalLayout header = new HorizontalLayout (new DrawerToggle (), logo, avatar, logOut);

        header.setDefaultVerticalComponentAlignment (FlexComponent.Alignment.CENTER);
        header.expand (logo);
        header.setWidthFull ();
        header.addClassNames ("pu-0", "px-m");

        addToNavbar (header);
    }
}
