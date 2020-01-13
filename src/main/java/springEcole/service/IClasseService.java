package springEcole.service;

import java.util.List;

import springEcole.bean.Classe;

public interface IClasseService {

	List<Classe> listall();
	void create( final Classe classe );
	void update( final List<Classe> cs );
	void remove( final Integer id );

}
