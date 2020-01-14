package springEcole.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springEcole.bean.Classe;
import springEcole.service.IClasseService;
import springEcole.service.IProfService;

@Controller
public class ClasseController {
	
	@Autowired
	private IClasseService service;
	
	@Autowired
	private IProfService profService;

	@RequestMapping( value="/classes", method=RequestMethod.GET )
	public String display( ModelMap pModel ) {
		final List<Classe> lList = service.listall();
		pModel.addAttribute("liste", lList);
		return "listClasses";		
	}
	
	@RequestMapping( value="/classes", method=RequestMethod.POST )
	public String suppr( @RequestParam(value="id") final Integer id, ModelMap pModel ) {
		service.remove( id );
		return display( pModel );
	}

	@RequestMapping( value="/editClasse/{id}", method=RequestMethod.GET )
	public String prepUpdate( @PathVariable(value="id") final Integer id, ModelMap pModel ) {
		Classe classe = service.get( id );
		return fillEditForm( pModel, id+"", classe.getNom(), classe.getProf().getNom() );
	}

	@RequestMapping( value="/editClasse", method=RequestMethod.GET )
	public String prepAdd( ModelMap pModel ) {
		return fillEditForm( pModel, "-1", "", "" );
	}
	
	@RequestMapping( value="/commitClasseEdit", method=RequestMethod.POST )
	public String addOrUpdate( ModelMap pModel,
			@RequestParam(value="id") final Integer id,
			@RequestParam(value="nom") final String nom,
			@RequestParam(value="prof") final Integer prof) {
		Classe classe = new Classe();
		classe.setNom( nom );
		classe.setProf( profService.get( prof ) );
		if( id == -1 ) { //creation
			service.create( classe );
		}
		else { //update
			classe.setId( id );
			service.update( Arrays.asList( new Classe[] {classe} ) );
		}
		return display( pModel );	
	}
	
	private String fillEditForm( ModelMap pModel, String... values ) {
		pModel.addAttribute( "fill_id", values[0] );
		pModel.addAttribute( "fill_nom", values[1] );
		pModel.addAttribute( "fill_prof", values[2] );
		pModel.addAttribute( "profs", profService.listall() );
		return "formClasse";
	}
}
