package springEcole.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springEcole.bean.Eleve;
import springEcole.bean.Sexe;
import springEcole.service.IClasseService;
import springEcole.service.IEleveService;

@Controller
public class EleveController {

	@Autowired
	private IEleveService service;

	@Autowired
	private IClasseService classeService;

	@RequestMapping( value="/eleves", method=RequestMethod.GET )
	public String display( ModelMap pModel ) {
		final List<Eleve> lList = service.listall();
		pModel.addAttribute("liste", lList);
		return "listEleves";		
	}
	
	@RequestMapping( value="/eleves", method=RequestMethod.POST )
	public String suppr( @RequestParam(value="id") final Integer id, ModelMap pModel ) {
		service.remove( id );
		return display( pModel );
	}

	@RequestMapping( value="/editEleve/{id}", method=RequestMethod.GET )
	public String prepUpdate( @PathVariable(value="id") final Integer id, ModelMap pModel ) {
		Eleve eleve = service.get( id );
		return fillEditForm(
			pModel,
			id+"",
			eleve.getNom(),
			eleve.getPrenom(),
			eleve.getClasse().getNom(),
			eleve.getDateNaissance().toString().split(" ")[0],//remove hours, minutes etc
			eleve.getAdresse(),
			eleve.getSexe().toString()
		);
	}

	@RequestMapping( value="/editEleve", method=RequestMethod.GET )
	public String prepAdd( ModelMap pModel ) {
		return fillEditForm( pModel, "-1", "", "", "", "", "", "" );
	}
	
	@RequestMapping( value="/commitEleveEdit", method=RequestMethod.POST )
	public String addOrUpdate( ModelMap pModel,
			@RequestParam(value="id") final Integer id,
			@RequestParam(value="nom") final String nom,
			@RequestParam(value="prenom") final String prenom,
			@RequestParam(value="classe") final Integer classe,
			@RequestParam(value="birth") final String birth,
			@RequestParam(value="adresse") final String adresse,
			@RequestParam(value="sexe") final Sexe sexe ) {
		Eleve eleve = new Eleve();
		eleve.setNom( nom );
		eleve.setPrenom( prenom );
		eleve.setClasse( classeService.get( classe ) );
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date madate = null;
		try {
			madate = sdf.parse(birth);
		}
		catch (ParseException e) { // abort
			return display( pModel );
		}
		eleve.setDateNaissance(madate);
		eleve.setAdresse( adresse );
		eleve.setSexe( sexe );
		//System.out.println( eleve );
		if( id == -1 ) { //creation
			System.err.println(eleve);
			service.create( eleve );
		}
		else { //update
			eleve.setId( id );
			service.update( Arrays.asList( new Eleve[] {eleve} ) );
		}
		return display( pModel );	
	}
	
	private String fillEditForm( ModelMap pModel, String... values ) {
		pModel.addAttribute( "fill_id", values[0] );
		pModel.addAttribute( "fill_nom", values[1] );
		pModel.addAttribute( "fill_prenom", values[2] );
		pModel.addAttribute( "fill_classe", values[3] );
		pModel.addAttribute( "fill_birth", values[4] );
		pModel.addAttribute( "fill_adresse", values[5] );
		pModel.addAttribute( "fill_sexe", values[6] );
		pModel.addAttribute( "classes", classeService.listall() );
		pModel.addAttribute( "sexes", Sexe.all() );
		return "formEleve";
	}

}
