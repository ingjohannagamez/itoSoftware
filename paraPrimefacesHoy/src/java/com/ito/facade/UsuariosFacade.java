/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.facade;

import com.ito.entity.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ito.entity.Usuarios_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ito.entity.Mercancias;
import com.ito.entity.Perfiles;
import com.ito.entity.Personas;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "paraPrimefacesHoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public boolean isMercanciasListEmpty(Usuarios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuarios> usuarios = cq.from(Usuarios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuarios, entity), cb.isNotEmpty(usuarios.get(Usuarios_.mercanciasList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Mercancias> findMercanciasList(Usuarios entity) {
        Usuarios mergedEntity = this.getMergedEntity(entity);
        List<Mercancias> mercanciasList = mergedEntity.getMercanciasList();
        mercanciasList.size();
        return mercanciasList;
    }

    public boolean isPerfilIdEmpty(Usuarios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuarios> usuarios = cq.from(Usuarios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuarios, entity), cb.isNotNull(usuarios.get(Usuarios_.perfilId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Perfiles findPerfilId(Usuarios entity) {
        return this.getMergedEntity(entity).getPerfilId();
    }

    public boolean isPersonaIdEmpty(Usuarios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuarios> usuarios = cq.from(Usuarios.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuarios, entity), cb.isNotNull(usuarios.get(Usuarios_.personaId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Personas findPersonaId(Usuarios entity) {
        return this.getMergedEntity(entity).getPersonaId();
    }

    @Override
    public Usuarios findWithParents(Usuarios entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuarios = cq.from(Usuarios.class);
        usuarios.fetch(Usuarios_.perfilId);
        cq.select(usuarios).where(cb.equal(usuarios, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
