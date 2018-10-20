package com.ito.controller;

import com.ito.prueba.entidad.Mercancias;
import com.ito.prueba.model.MercanciasFacade;
import com.ito.util.SessionItoSoftware;
import controller.util.JsfUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @Copyrigth [2018] www.ito-software.com
 * @MercanciasController
 * @Descripcion Controlador encargado de administrar las mercacioas por usuarios
 * @author Johann Andres Agamez Ferres
 * @Fecha Creación: 20/10/2018
 * @Fecha ultima modificación: 21/10/2018
 */
@Named(value = "mercanciasController")
@ViewScoped
public class MercanciasController extends AbstractController<Mercancias> implements Serializable {

    @EJB
    private MercanciasFacade ejbFacade;

    private SessionItoSoftware sessionItoSoftware;

    public MercanciasController() {
        super(Mercancias.class);
    }

    @PostConstruct
    private void init() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            this.sessionItoSoftware = (SessionItoSoftware) session.getAttribute("sessionItoSoftware");
            this.setItems(this.ejbFacade.findAll());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cargar el listado");
        }
    }

    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(this.getItemClass().getSimpleName() + "Created");
        this.getSelected().setUsuarioRegistroId(this.sessionItoSoftware.getDatosAcceso());
        persist(AbstractController.PersistAction.CREATE, msg);
    }

    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(this.getItemClass().getSimpleName() + "Updated");
        this.getSelected().setUsuarioRegistroId(this.sessionItoSoftware.getDatosAcceso());
        persist(AbstractController.PersistAction.UPDATE, msg);
    }

    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(this.getItemClass().getSimpleName() + "Deleted");
        persist(AbstractController.PersistAction.DELETE, msg);
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (this.getSelected() != null) {
            try {
                if (null != persistAction) {
                    switch (persistAction) {
                        case CREATE:
                            ejbFacade.create(this.getSelected());
                            refreshItems();
                            break;
                        case UPDATE:
                            ejbFacade.edit(this.getSelected());
                            break;
                        case DELETE:
                            ejbFacade.remove(this.getSelected());
                            this.getItems().clear();
                            this.setItems(this.ejbFacade.findAll());
                            break;
                        default:
                            throw new Exception();
                    }
                    JsfUtil.addSuccessMessage(successMessage);
                }
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Date sumarRestarDiasFecha(Mercancias mercancias) {
        int dias;
        Calendar calendar = Calendar.getInstance();
        try {
            if (mercancias.getDestinatarioId().getUsuarios() != null && 
                mercancias.getDestinatarioId().getUsuarios().getPerfilId().getPerfil().equals("Empleado")) {
                dias = 1;
            } else {
                dias = 3;
            }

            calendar.setTime(mercancias.getFechaSalida());
            calendar.add(Calendar.DAY_OF_YEAR, dias);
        } catch (Exception e) {
            calendar.setTime(mercancias.getFechaSalida());
        }

        return calendar.getTime();
    }

}
