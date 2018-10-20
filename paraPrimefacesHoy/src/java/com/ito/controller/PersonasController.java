package com.ito.controller;

import com.ito.entity.Personas;
import com.ito.entity.Mercancias;
import java.util.List;
import com.ito.facade.PersonasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "personasController")
@ViewScoped
public class PersonasController extends AbstractController<Personas> {

    @Inject
    private UsuariosController usuariosController;

    // Flags to indicate if child collections are empty
    private boolean isMercanciasListEmpty;

    public PersonasController() {
        // Inform the Abstract parent controller of the concrete Personas Entity
        super(Personas.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuariosController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsMercanciasListEmpty();
    }

    public boolean getIsMercanciasListEmpty() {
        return this.isMercanciasListEmpty;
    }

    private void setIsMercanciasListEmpty() {
        Personas selected = this.getSelected();
        if (selected != null) {
            PersonasFacade ejbFacade = (PersonasFacade) this.getFacade();
            this.isMercanciasListEmpty = ejbFacade.isMercanciasListEmpty(selected);
        } else {
            this.isMercanciasListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Mercancias entities that
     * are retrieved from Personas and returns the navigation outcome.
     *
     * @return navigation outcome for Mercancias page
     */
    public String navigateMercanciasList() {
        Personas selected = this.getSelected();
        if (selected != null) {
            PersonasFacade ejbFacade = (PersonasFacade) this.getFacade();
            List<Mercancias> selectedMercanciasList = ejbFacade.findMercanciasList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Mercancias_items", selectedMercanciasList);
        }
        return "/app/mercancias/index";
    }

    /**
     * Sets the "selected" attribute of the Usuarios controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarios(ActionEvent event) {
        Personas selected = this.getSelected();
        if (selected != null && usuariosController.getSelected() == null) {
            usuariosController.setSelected(selected.getUsuarios());
        }
    }

}
