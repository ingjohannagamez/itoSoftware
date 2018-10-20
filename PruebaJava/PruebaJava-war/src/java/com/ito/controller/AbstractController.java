package com.ito.controller;

import controller.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class<T> itemClass;
    private T selected;
    private List<T> items;

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
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

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
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

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            setItems((List<T>) paramItems);
        }
    }

    private List<T> refreshItem(T item, Collection<T> items) {
        // Use List#set to replace the existing instance of this entity
        // If items is not a List, convert the Collection to a List
        List<T> itemList;
        if (this.items instanceof List) {
            itemList = (List<T>) items;
        } else {
            itemList = new ArrayList<>(items);
        }
        int i = itemList.indexOf(item);
        if (i >= 0) {
            try {
                itemList.set(i, item);
            } catch (UnsupportedOperationException ex) {
                return refreshItem(item, new ArrayList<>(items));
            }
        }
        return itemList;
    }
}
