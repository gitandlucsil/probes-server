package br.com.andlucsil.probesserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andlucsil.probesserver.repository.ProbesIdfRepository;

@Controller
@RequestMapping("/probesidfs")
public class ProbesIdfController {
	
	@Autowired
	ProbesIdfRepository probesidfrepository;
	
}
