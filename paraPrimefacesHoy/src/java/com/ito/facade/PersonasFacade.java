/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.facade;

import com.ito.entity.Personas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ito.entity.Personas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ito.entity.Mercancias;
import com.ito.entity.Usuarios;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> {

    @PersistenceContext(unitName = "paraPrimefacesHoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }

    public boolean isMercanciasListEmpty(Personas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Personas> personas = cq.from(Personas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(personas, entity), cb.isNotEmpty(personas.get(Personas_.mercanciasList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Mercancias> findMercanciasList(Personas entity) {
        Personas mergedEntity = this.getMergedEntity(entity);
        List<Mercancias> mercanciasList = mergedEntity.getMercanciasList();
        mercanciasList.size();
        return mercanciasList;
    }

    public boolean isUsuariosEmpty(Personas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Personas> personas = cq.from(Personas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(personas, entity), cb.isNotNull(personas.get(Personas_.usuarios)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuarios findUsuarios(Personas entity) {
        return this.getMergedEntity(entity).getUsuarios();
    }
    
}
