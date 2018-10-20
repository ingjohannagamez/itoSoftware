package com.ito.controller;

import com.ito.entity.Mercancias;
import com.ito.facade.MercanciasFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "mercanciasController")
@ViewScoped
public class MercanciasController extends AbstractController<Mercancias> {

    @Inject
    private PersonasController destinatarioIdController;
    @Inject
    private UsuariosController usuarioRegistroIdController;

    public MercanciasController() {
        // Inform the Abstract parent controller of the concrete Mercancias Entity
        super(Mercancias.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        destinatarioIdController.setSelected(null);
        usuarioRegistroIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Personas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDestinatarioId(ActionEvent event) {
        Mercancias selected = this.getSelected();
        if (selected != null && destinatarioIdController.getSelected() == null) {
            destinatarioIdController.setSelected(selected.getDestinatarioId());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuarios controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioRegistroId(ActionEvent event) {
        Mercancias selected = this.getSelected();
        if (selected != null && usuarioRegistroIdController.getSelected() == null) {
            usuarioRegistroIdController.setSelected(selected.getUsuarioRegistroId());
        }
    }

}
