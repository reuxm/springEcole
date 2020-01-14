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

import springEcole.bean.Prof;
import springEcole.bean.Sexe;
import springEcole.service.IProfService;

@Controller
public class ProfController {

	@Autowired
	private IProfService service;

	@RequestMapping( value="/profs", method=RequestMethod.GET )
	public String display( ModelMap pModel ) {
		final List<Prof> lList = service.listall();
		pModel.addAttribute("liste", lList);
		return "listProfs";		
	}
	
	@RequestMapping( value="/profs", method=RequestMethod.POST )
	public String suppr( @RequestParam(value="id") final Integer id, ModelMap pModel ) {
		service.remove( id );
		return display( pModel );
	}

	@RequestMapping( value="/editProf/{id}", method=RequestMethod.GET )
	public String prepUpdate( @PathVariable(value="id") final Integer id, ModelMap pModel ) {
		Prof prof = service.get( id );
		return fillEditForm(
			pModel,
			id+"",
			prof.getNom(),
			prof.getPrenom(),
			prof.getDateNaissance().toString().split(" ")[0],//remove hours, minutes etc
			prof.getAdresse(),
			prof.getSexe().toString()
		);
	}

	@RequestMapping( value="/editProf", method=RequestMethod.GET )
	public String prepAdd( ModelMap pModel ) {
		return fillEditForm( pModel, "-1", "", "", "", "", "" );
	}
	
	@RequestMapping( value="/commitProfEdit", method=RequestMethod.POST )
	public String addOrUpdate( ModelMap pModel,
			@RequestParam(value="id") final Integer id,
			@RequestParam(value="nom") final String nom,
			@RequestParam(value="prenom") final String prenom,
			@RequestParam(value="birth") final String birth,
			@RequestParam(value="adresse") final String adresse,
			@RequestParam(value="sexe") final Sexe sexe) {
		Prof prof = new Prof();
		prof.setNom( nom );
		prof.setPrenom(prenom);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date madate = null;
		try {
			madate = sdf.parse(birth);
		}
		catch (ParseException e) { // abort
			return display( pModel );
		}
		prof.setDateNaissance(madate);
		prof.setAdresse( adresse );
		prof.setSexe( sexe );
		if( id == -1 ) { //creation
			//System.out.println(prof);
			service.create( prof );
		}
		else { //update
			prof.setId( id );
			service.update( Arrays.asList( new Prof[] {prof} ) );
		}
		return display( pModel );	
	}
	
	private String fillEditForm( ModelMap pModel, String... values ) {
		pModel.addAttribute( "fill_id", values[0] );
		pModel.addAttribute( "fill_nom", values[1] );
		pModel.addAttribute( "fill_prenom", values[2] );
		pModel.addAttribute( "fill_birth", values[3] );
		pModel.addAttribute( "fill_adresse", values[4] );
		pModel.addAttribute( "fill_sexe", values[5] );
		pModel.addAttribute( "sexes",  Sexe.all() );
		return "formProf";
	}
}
