/*
 * EmployerJobCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.roles.Manager;
import acme.entities.spamWord.SpamWord;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;
	

	// AbstractCreateService<Manager, Task> interface -------------------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
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
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		

		final List<Customization> repo= this.repository.findCustomization();
		
		String res;
		res= entity.getTitle().concat(" ").concat(entity.getDescription());
			
		
		
		if(ManagerTaskCreateService.esSpam(repo.get(0).getPalabrasSpam(), res, repo.get(0).getTolerancia())) {
			
			errors.state(request, false, "description", "anonymous.shout.create.error.label.text");
		}
		
		if (!errors.hasErrors("finalMoment")) {
			final Date finalMom = entity.getFinalMoment();
			final Date initialMom = entity.getInitialMoment();
			
			errors.state(request, finalMom.compareTo(initialMom) > 0, "finalMoment", "manager.task.create.error.label.finalMoment");
		}
		
		if (!errors.hasErrors("workload")) {
			final Date finalMom = entity.getFinalMoment();
			final Date initialMom = entity.getInitialMoment();
			
			final Long maxWorkload = finalMom.getTime() - initialMom.getTime();
			
			errors.state(request, entity.getWorkload()*3600000 <= maxWorkload, "workload", "manager.task.create.error.label.workload");
		}
		
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "initialMoment", "finalMoment", "workload", "description");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Manager manager;

		manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());
		result = new Task();
		result.setManager(manager);
		result.setIsPublic(false);

		return result;
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
