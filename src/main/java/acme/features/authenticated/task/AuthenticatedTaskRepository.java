package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{
	
	
	
	@Query("select t from Task t where t.isPublic = true and t.finalMoment <= current_timestamp() order by (t.finalMoment-t.initialMoment), t.workload asc")
	Collection<Task> findManyPublicNonFinished();

	
	
	@Query("select t from Task t where t.id = ?1 and t.isPublic = true and t.finalMoment <= current_timestamp()")
	Task findOnePublicTaskById(int id);
	
	
	Task findById(int id);
}
