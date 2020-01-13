package springEcole.dao;

import java.util.List;

import springEcole.bean.Prof;

public interface IProfDAO {

	List<Prof> listall();
	void create( final Prof p );
	void update( final Prof p );
	void remove( final Prof p );

}
