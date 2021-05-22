package acme.features.administrator.spamword;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spamWord.SpamWord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spamword/")
public class AdministratorSpamwordController extends AbstractController<Administrator, SpamWord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamwordListService listService;
	
	@Autowired
	protected AdministratorSpamwordShowService showService;
	
	@Autowired
	protected AdministratorSpamwordUpdateService updateService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
	
}
