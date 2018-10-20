package com.ito.controller;

import controller.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;


/**
 * @Copyrigth [2018] www.ito-software.com
 * @AbstractController
 * @Descripcion Clase abstracta encargada de administrar las variables de las vistas
 * para poder ser usadas en los controladores.
 * @author Johann Andres Agamez Ferres
 * @Fecha Creación: 20/10/2018
 * @Fecha ultima modificación: 21/10/2018
 */
public abstract class AbstractController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class<T> itemClass;
    private T selected;
    private List<T> items;

    public AbstractController() {
    }

    public enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }
    
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void refreshItems() {
        this.items.add(this.selected);
    }

    public Class<T> getItemClass() {
        return itemClass;
    }

    public void setItemClass(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        this.selected = selected;
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

}
