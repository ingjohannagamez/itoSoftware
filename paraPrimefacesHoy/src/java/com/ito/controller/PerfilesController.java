package com.ito.controller;

import com.ito.entity.Perfiles;
import com.ito.entity.Usuarios;
import java.util.List;
import com.ito.facade.PerfilesFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "perfilesController")
@ViewScoped
public class PerfilesController extends AbstractController<Perfiles> {

    // Flags to indicate if child collections are empty
    private boolean isUsuariosListEmpty;

    public PerfilesController() {
        // Inform the Abstract parent controller of the concrete Perfiles Entity
        super(Perfiles.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuariosListEmpty();
    }

    public boolean getIsUsuariosListEmpty() {
        return this.isUsuariosListEmpty;
    }

    private void setIsUsuariosListEmpty() {
        Perfiles selected = this.getSelected();
        if (selected != null) {
            PerfilesFacade ejbFacade = (PerfilesFacade) this.getFacade();
            this.isUsuariosListEmpty = ejbFacade.isUsuariosListEmpty(selected);
        } else {
            this.isUsuariosListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuarios entities that
     * are retrieved from Perfiles and returns the navigation outcome.
     *
     * @return navigation outcome for Usuarios page
     */
    public String navigateUsuariosList() {
        Perfiles selected = this.getSelected();
        if (selected != null) {
            PerfilesFacade ejbFacade = (PerfilesFacade) this.getFacade();
            List<Usuarios> selectedUsuariosList = ejbFacade.findUsuariosList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuarios_items", selectedUsuariosList);
        }
        return "/app/usuarios/index";
    }

}
