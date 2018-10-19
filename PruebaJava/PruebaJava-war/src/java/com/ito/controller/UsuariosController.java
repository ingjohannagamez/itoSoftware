/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.controller;

import com.ito.prueba.model.UsuariosFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author pipo0
 */
@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;

    public UsuariosController() {
    }

    /**
     *
     * @return
     */
    public List<com.ito.prueba.entidad.Usuarios> finAll() {
        return this.usuariosFacade.findAll();
    }

}
