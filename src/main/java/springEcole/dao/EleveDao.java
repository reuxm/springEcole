package springEcole.dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import springEcole.bean.Eleve;

@Repository
public class EleveDao extends Dao<Eleve> implements IEleveDAO {

	protected EleveDao() {
		super( Eleve.class );
	}
	
	public void remove( final Eleve e ) {
		super.remove(entityManager.getReference( Eleve.class, e.getId()));
	}
	
	public void update( final Eleve e ) {
		final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaUpdate<Eleve> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate( Eleve.class );
		final Root<Eleve> lRoot = lCriteriaUpdate.from( Eleve.class);
		final Path<Eleve> lPath = lRoot.get("id");
		lCriteriaUpdate.where( lCriteriaBuilder.equal(lPath, e.getId()) );
		lCriteriaUpdate.set( "nom", e.getNom() );
		lCriteriaUpdate.set( "prenom", e.getPrenom() );
		lCriteriaUpdate.set( "classe", e.getClasse() );
		lCriteriaUpdate.set( "date_naissance", e.getDateNaissance() );
		lCriteriaUpdate.set( "adresse", e.getAdresse() );
		lCriteriaUpdate.set( "sexe", e.getSexe() );
		
		final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
		final int lRowCount = lQuery.executeUpdate();
		if (lRowCount != 1) {
			final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
			final String lSql = lHQuery.getQueryString();
			throw new RuntimeException("Nombre d'occurences (" + lRowCount + ") modifiés différent de 1 pour " + lSql);
		}
	}

}
