/*
by Jakub Wawak
kubawawak@gmail.com
all rights reserved
*/
package com.jakubwawak.server.website;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * Main application web view
 */
@PageTitle("terminaldo")
@Route("template")
public class TemplateView extends VerticalLayout {


    /**
     * Constructor
     */
    public TemplateView(){
        addClassName("view");
        prepareLayout();
    }

    /**
     * Function for preparing components
     */
    void prepareComponents(){

    }

    /**
     * Function for preparing layout data
     */
    void prepareLayout(){
        prepareComponents();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}