package springEcole.dao;

import java.util.List;

import springEcole.bean.Eleve;

public interface IEleveDAO {

	List<Eleve> listall();
	void create( final Eleve e );
	void update( final Eleve e );
	void remove( final Eleve e );
	
}
