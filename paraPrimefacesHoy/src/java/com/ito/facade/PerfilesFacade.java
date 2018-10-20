/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.facade;

import com.ito.entity.Perfiles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ito.entity.Perfiles_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ito.entity.Usuarios;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class PerfilesFacade extends AbstractFacade<Perfiles> {

    @PersistenceContext(unitName = "paraPrimefacesHoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilesFacade() {
        super(Perfiles.class);
    }

    public boolean isUsuariosListEmpty(Perfiles entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Perfiles> perfiles = cq.from(Perfiles.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(perfiles, entity), cb.isNotEmpty(perfiles.get(Perfiles_.usuariosList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Usuarios> findUsuariosList(Perfiles entity) {
        Perfiles mergedEntity = this.getMergedEntity(entity);
        List<Usuarios> usuariosList = mergedEntity.getUsuariosList();
        usuariosList.size();
        return usuariosList;
    }
    
}
