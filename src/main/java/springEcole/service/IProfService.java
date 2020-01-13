package springEcole.service;

import java.util.List;

import springEcole.bean.Prof;

public interface IProfService {

	List<Prof> listall();
	void create( final Prof p );
	void update( final List<Prof> ps );
	void remove( final Integer id );

}
