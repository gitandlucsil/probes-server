package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andlucsil.probesserver.repository.ProbesValuesRepository;

@Controller
@RequestMapping("/probesvalues")
public class ProbesValuesController {

	@Autowired
	ProbesValuesRepository probesvaluesrepository;
}
