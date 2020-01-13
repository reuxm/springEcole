package springEcole.dao;

import java.util.List;

import springEcole.bean.Classe;

public interface IClasseDAO {

	List<Classe> listall();
	void create( final Classe c );
	void update( final Classe c );
	void remove( final Classe c );

}
