/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.controller;

import com.ito.prueba.entidad.Usuarios;
import com.ito.prueba.model.UsuariosFacade;
import com.ito.util.SessionItoSoftware;
import controller.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.PrimeFaces;

/**
 * @Copyrigth [2014] www.softwarevital.com
 * @LoginController
 * @Descripcion Controlador encargado de administrar los accesos de los usuarios
 * @author Johann Andres Agamez Ferres
 * @Fecha Creación: 05/11/2014
 * @Fecha ultima modificación: 06/11/2014
 */
@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController extends AbstractController<Usuarios> implements Serializable {

    @EJB
    private UsuariosFacade ejbFacade;

    private String username;
    private String password;

    public UsuariosController() {
        super(Usuarios.class);
    }

    @PostConstruct
    private void init() {
        try {
            this.setItems(this.ejbFacade.findAll());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cargar el listado");
        }
    }

    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(this.getItemClass().getSimpleName() + "Created");
        persist(AbstractController.PersistAction.CREATE, msg);
    }

    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(this.getItemClass().getSimpleName() + "Updated");
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

    public void login(ActionEvent event) {
        FacesMessage message = null;
        boolean loggedIn = false;

        Usuarios objTemp = validarAcceso();

        if (objTemp != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            SessionItoSoftware sessionItoSoftware = new SessionItoSoftware();
            
            sessionItoSoftware.setIDSESION(session.getId());
            sessionItoSoftware.setDatosAcceso(objTemp);
            sessionItoSoftware.setMenuVisible(true);
            
            session.setAttribute("sessionItoSoftware", sessionItoSoftware);
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        JsfUtil.addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        PrimeFaces.current().executeScript("actualizar()");
    }

    private Usuarios validarAcceso() {
        Usuarios respuesta;
        try {
            respuesta = this.ejbFacade.validarAcceso(this.username, this.password);
        } catch (Exception e) {
            respuesta = null;
        }
        return respuesta;
    }

    public void closeSession() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        PrimeFaces.current().executeScript("actualizar()");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
