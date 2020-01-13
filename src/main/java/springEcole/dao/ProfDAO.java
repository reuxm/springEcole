package springEcole.dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import springEcole.bean.Prof;

public class ProfDAO extends Dao<Prof> implements IProfDAO {

	protected ProfDAO() {
		super( Prof.class );
	}
	
	public void remove( final Prof p ) {
		super.remove(entityManager.getReference( Prof.class, p.getId()));
	}
	
	public void update( final Prof p ) {
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaUpdate<Prof> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate( Prof.class );
		final Root<Prof> lRoot = lCriteriaUpdate.from( Prof.class);
		final Path<Prof> lPath = lRoot.get("id");
		lCriteriaUpdate.where( lCriteriaBuilder.equal(lPath, p.getId()) );
		lCriteriaUpdate.set( "nom", p.getNom() );
		lCriteriaUpdate.set( "prenom", p.getPrenom() );
		lCriteriaUpdate.set( "date_naissance", p.getDateNaissance() );
		lCriteriaUpdate.set( "adresse", p.getAdresse() );
		lCriteriaUpdate.set( "sexe", p.getSexe() );
		
		final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
		final int lRowCount = lQuery.executeUpdate();
		if (lRowCount != 1) {
			final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
			final String lSql = lHQuery.getQueryString();
			throw new RuntimeException("Nombre d'occurences (" + lRowCount + ") modifiés différent de 1 pour " + lSql);
		}
	}

}
