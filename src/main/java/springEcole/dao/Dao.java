package springEcole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

@Repository
public abstract class Dao<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> dataType;
	
	protected Dao(Class<T> data) {
		dataType = data;
	}
	
	public List<T> listall() {
		
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> lCriteriaQuery = lCriteriaBuilder.createQuery( dataType );
		lCriteriaQuery.select( lCriteriaQuery.from( dataType ) );
		final TypedQuery<T> lTypedQuery = entityManager.createQuery(lCriteriaQuery);
		return lTypedQuery.getResultList();
		
	}
	
	public void create( final T t ) {
		entityManager.persist( t );
	}

	protected void remove(T t) {
		entityManager.remove( t );
	}
	
}
