package springEcole.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import springEcole.bean.Prof;
import springEcole.dao.IProfDAO;

public class ProfService implements IProfService {

	@Autowired
	private IProfDAO dao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Prof> listall() {
		return dao.listall();
	}

	@Override
	@Transactional(readOnly=true)
	public void create(Prof p) {
		dao.create( p );
	}

	@Override
	@Transactional(readOnly=true)
	public void update(List<Prof> profs) {
		profs.forEach(
			prof -> dao.update( prof )
		);
	}

	@Override
	@Transactional(readOnly=true)
	public void remove(Integer id) {
		final Prof prof = new Prof();
		prof.setId(id);
		dao.remove( prof );
	}

}
