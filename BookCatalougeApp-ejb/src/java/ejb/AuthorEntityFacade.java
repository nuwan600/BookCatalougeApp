/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nuwan600
 */
@Stateless
public class AuthorEntityFacade extends AbstractFacade<AuthorEntity> {
    @PersistenceContext(unitName = "BookCatalougeApp-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorEntityFacade() {
        super(AuthorEntity.class);
    }
    
}
