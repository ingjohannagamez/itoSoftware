package com.ito.util;

import com.ito.prueba.entidad.Usuarios;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Johann Agamez
 */
@ManagedBean
@SessionScoped
public class SessionItoSoftware implements Serializable {

    private Map<String, Object> mapSession;
    private Usuarios datosAcceso;
    private String IDSESION;
    private boolean menuVisible;

    public void cerrarSesion() {
        setDatosAcceso(null);
        setIDSESION("");
        setMenuVisible(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public Map<String, Object> getMapSession() {
        mapSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return mapSession;
    }

    public String getRowsPerPageTemplate() {
        return "12,24,36,48,60";
    }

    public String getRows() {
        return "12";
    }

    public String getCacheTimeout() {
        return "240000";
    }

    public boolean isCaching() {
        return true;
    }

    public Usuarios getDatosAcceso() {
        return datosAcceso;
    }

    public void setDatosAcceso(Usuarios datosAcceso) {
        this.datosAcceso = datosAcceso;
    }

    public String getIDSESION() {
        return IDSESION;
    }

    public void setIDSESION(String IDSESION) {
        this.IDSESION = IDSESION;
    }

    public boolean isMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(boolean menuVisible) {
        this.menuVisible = menuVisible;
    }

}
