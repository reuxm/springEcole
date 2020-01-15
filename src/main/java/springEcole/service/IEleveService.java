package springEcole.service;

import java.util.List;

import springEcole.bean.Eleve;

public interface IEleveService {

	List<Eleve> listall();
	Eleve get( final Integer id);
	void create( final Eleve e );
	void update( final List<Eleve> es );
	void remove( final Integer id );

}
