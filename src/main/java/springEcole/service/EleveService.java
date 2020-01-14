package springEcole.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springEcole.bean.Eleve;
import springEcole.dao.IEleveDAO;

@Service
public class EleveService implements IEleveService {

	@Autowired
	private IEleveDAO dao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Eleve> listall() {
		return dao.listall();
	}

	@Override
	@Transactional
	public void create(Eleve e) {
		dao.create( e );
	}

	@Override
	@Transactional
	public void update(List<Eleve> eleves) {
		eleves.forEach(
			eleve -> dao.update(eleve)
		);
	}

	@Override
	@Transactional
	public void remove(Integer id) {
		final Eleve eleve = new Eleve();
		eleve.setId(id);
		dao.remove( eleve );
	}

}
