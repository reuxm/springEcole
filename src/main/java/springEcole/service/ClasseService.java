package springEcole.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springEcole.bean.Classe;
import springEcole.dao.IClasseDAO;

@Service
public class ClasseService implements IClasseService {
	
	@Autowired
	private IClasseDAO dao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Classe> listall() {
		return dao.listall();
	}

	@Override
	@Transactional
	public void create(Classe c) {
		dao.create( c );
	}

	@Override
	@Transactional
	public void update(List<Classe> classes) {
		classes.forEach(
			classe -> dao.update( classe )
		);
	}

	@Override
	@Transactional
	public void remove(Integer id) {
		final Classe classe = new Classe();
		classe.setId(id);
		dao.remove( classe );
	}

	@Override
	@Transactional
	public Classe get(Integer id) {
		return dao.get( id );
	}
	
}
