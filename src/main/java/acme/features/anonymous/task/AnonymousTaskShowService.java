package acme.features.anonymous.task;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		boolean sol = true;
		
		Task result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);
		if(!result.getIsPublic() || !result.getFinalMoment().after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
			sol = false;

		return sol;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "initialMoment", "finalMoment", "workload", "description", "isPublic");
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePublicTaskById(id);

		return result;
	}
	
}
