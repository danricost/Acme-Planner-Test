/*
 * AnonymousShoutCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.shouts.Shout;
import acme.entities.spamWord.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository repository;

	// AbstractCreateService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");

		return result;
	}

	public static  boolean esSpam(final List<SpamWord> palabrasSpam, final String texto, final Double tolerancia) {
		boolean boleano=false;
		
		int npalabrasspam=0;
		
		int palabrasCompuestas=0;
		for(int i=0; i<palabrasSpam.size(); i++) {
			
			if(texto.contains(palabrasSpam.get(i).getPalabraSpam())){
				
				
				
				final String[] pru=texto.concat(".").split(palabrasSpam.get(i).getPalabraSpam());
				
				npalabrasspam+= pru.length-1;
				
				if(palabrasSpam.get(i).getPalabraSpam().split(" ").length>=2) {
					palabrasCompuestas+= (palabrasSpam.get(i).getPalabraSpam().split(" ").length-1) *  (pru.length-1);
				}
			}
		}
		
		final String[] grito= texto.replace(".", "").replace(",", "").split(" ");	
		
		final double porcentaje= ((double)npalabrasspam/(double)(grito.length-palabrasCompuestas))*100.;
		
		if(porcentaje >=tolerancia) {
			
			boleano=true;
		}
		
		
		return boleano;
	}

	
	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<Customization> repo= this.repository.findCustomization();
		
		String res;
		res= entity.getAuthor().concat(" ").concat(entity.getText());
			
		
		
		//if(AnonymousShoutCreateService.esSpam(repo.get(0).getPalabrasSpam(), entity.getText(), repo.get(0).getTolerancia())) {
		if(AnonymousShoutCreateService.esSpam(repo.get(0).getPalabrasSpam(), res, repo.get(0).getTolerancia())) {
			
			errors.state(request, false, "text", "anonymous.shout.create.error.label.text");
		}
		
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
