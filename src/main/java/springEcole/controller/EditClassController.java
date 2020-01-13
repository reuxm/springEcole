package springEcole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springEcole.bean.Classe;
import springEcole.service.IClasseService;

@Controller
public class EditClassController {
	
	@Autowired
	private IClasseService service;

	@RequestMapping( value="/editClasse/{id}", method=RequestMethod.GET )
	public String update( @PathVariable(value="id") final Integer id, ModelMap pModel ) {
		Classe classe = service.get( id );
		pModel.addAttribute( "act", "update" );
		pModel.addAttribute( "fill_id", id );
		pModel.addAttribute( "fill_nom", classe.getNom() );
		pModel.addAttribute("fill_prof", classe.getProf().getNom() );
		return add( pModel );
	}

	@RequestMapping( value="/editClasse", method=RequestMethod.POST )
	public String add( ModelMap pModel ) {
		pModel.addAttribute( "act", "new" );
		return "formClasse";
	}

}
