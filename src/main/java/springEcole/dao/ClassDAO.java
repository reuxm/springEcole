package springEcole.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import springEcole.bean.Classe;

@Repository
public class ClassDAO extends Dao<Classe> implements IClasseDAO {

	protected ClassDAO() {
		super( Classe.class );
	}
	
	public void remove( final Classe c ) {
		super.remove(entityManager.getReference( Classe.class, c.getId()));
	}
	
	public List<Classe> listall() {
		
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Classe> lCriteriaQuery = lCriteriaBuilder.createQuery( Classe.class );
		lCriteriaQuery.select( lCriteriaQuery.from( Classe.class ) );
		final TypedQuery<Classe> lTypedQuery = entityManager.createQuery(lCriteriaQuery);
		return lTypedQuery.getResultList();
		
	}
	
	
	public void update( final Classe c ) {
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaUpdate<Classe> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate( Classe.class );
		final Root<Classe> lRoot = lCriteriaUpdate.from( Classe.class);
		final Path<Classe> lPath = lRoot.get("id");
		lCriteriaUpdate.where( lCriteriaBuilder.equal(lPath, c.getId()) );
		lCriteriaUpdate.set( "nom", c.getNom() );
		lCriteriaUpdate.set( "prof", c.getProf() );
		
		final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
		final int lRowCount = lQuery.executeUpdate();
		if (lRowCount != 1) {
			final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
			final String lSql = lHQuery.getQueryString();
			throw new RuntimeException("Nombre d'occurences (" + lRowCount + ") modifiés différent de 1 pour " + lSql);
		}
	}

}
