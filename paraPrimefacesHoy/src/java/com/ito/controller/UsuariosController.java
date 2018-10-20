package com.ito.controller;

import com.ito.entity.Usuarios;
import com.ito.entity.Mercancias;
import java.util.List;
import com.ito.facade.UsuariosFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController extends AbstractController<Usuarios> {

    @Inject
    private PerfilesController perfilIdController;
    @Inject
    private PersonasController personaIdController;

    // Flags to indicate if child collections are empty
    private boolean isMercanciasListEmpty;

    public UsuariosController() {
        // Inform the Abstract parent controller of the concrete Usuarios Entity
        super(Usuarios.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        perfilIdController.setSelected(null);
        personaIdController.setSelected(null);
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
        Usuarios selected = this.getSelected();
        if (selected != null) {
            UsuariosFacade ejbFacade = (UsuariosFacade) this.getFacade();
            this.isMercanciasListEmpty = ejbFacade.isMercanciasListEmpty(selected);
        } else {
            this.isMercanciasListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Mercancias entities that
     * are retrieved from Usuarios and returns the navigation outcome.
     *
     * @return navigation outcome for Mercancias page
     */
    public String navigateMercanciasList() {
        Usuarios selected = this.getSelected();
        if (selected != null) {
            UsuariosFacade ejbFacade = (UsuariosFacade) this.getFacade();
            List<Mercancias> selectedMercanciasList = ejbFacade.findMercanciasList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Mercancias_items", selectedMercanciasList);
        }
        return "/app/mercancias/index";
    }

    /**
     * Sets the "selected" attribute of the Perfiles controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePerfilId(ActionEvent event) {
        Usuarios selected = this.getSelected();
        if (selected != null && perfilIdController.getSelected() == null) {
            perfilIdController.setSelected(selected.getPerfilId());
        }
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePersonaId(ActionEvent event) {
        Usuarios selected = this.getSelected();
        if (selected != null && personaIdController.getSelected() == null) {
            personaIdController.setSelected(selected.getPersonaId());
        }
    }

}
