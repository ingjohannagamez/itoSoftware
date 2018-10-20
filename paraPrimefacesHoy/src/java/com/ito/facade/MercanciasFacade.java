/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.facade;

import com.ito.entity.Mercancias;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ito.entity.Mercancias_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.ito.entity.Personas;
import com.ito.entity.Usuarios;

/**
 *
 * @author pipo0
 */
@Stateless
public class MercanciasFacade extends AbstractFacade<Mercancias> {

    @PersistenceContext(unitName = "paraPrimefacesHoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MercanciasFacade() {
        super(Mercancias.class);
    }

    public boolean isDestinatarioIdEmpty(Mercancias entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mercancias> mercancias = cq.from(Mercancias.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(mercancias, entity), cb.isNotNull(mercancias.get(Mercancias_.destinatarioId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Personas findDestinatarioId(Mercancias entity) {
        return this.getMergedEntity(entity).getDestinatarioId();
    }

    public boolean isUsuarioRegistroIdEmpty(Mercancias entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mercancias> mercancias = cq.from(Mercancias.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(mercancias, entity), cb.isNotNull(mercancias.get(Mercancias_.usuarioRegistroId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuarios findUsuarioRegistroId(Mercancias entity) {
        return this.getMergedEntity(entity).getUsuarioRegistroId();
    }

    @Override
    public Mercancias findWithParents(Mercancias entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Mercancias> cq = cb.createQuery(Mercancias.class);
        Root<Mercancias> mercancias = cq.from(Mercancias.class);
        mercancias.fetch(Mercancias_.destinatarioId, JoinType.LEFT);
        mercancias.fetch(Mercancias_.usuarioRegistroId, JoinType.LEFT);
        cq.select(mercancias).where(cb.equal(mercancias, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
