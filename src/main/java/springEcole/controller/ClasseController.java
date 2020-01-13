package springEcole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springEcole.bean.Classe;
import springEcole.service.IClasseService;

@Controller
@RequestMapping(value="/classes")
public class ClasseController {
	
	@Autowired
	private IClasseService service;

	@RequestMapping( method=RequestMethod.GET )
	public String display( ModelMap pModel ) {
		final List<Classe> lList = service.listall();
		pModel.addAttribute("liste", lList);
		return "listClasses";		
	}
	
	@RequestMapping( method=RequestMethod.POST )
	public String suppr( @RequestParam(value="id") final Integer id, ModelMap pModel ) {
		service.remove( id );
		return display( pModel );
	}
}
