/*
 * EmployerJobRepository.java
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

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customization.Customization;
import acme.entities.roles.Manager;
import acme.entities.spamWord.SpamWord;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("select c from Customization c")
	List<Customization> findCustomization();
	
	@Query("select s from SpamWord s")
	Collection<SpamWord> getListSpamWord();
	
	@Query("select s from SpamWord s where s.id = ?1")
	SpamWord getSpamWordById(int id);
	
	@Query("select j from Task j where j.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select t from Task t where t.manager.id = ?1")
	Collection<Task> findManyByManagerId(int managerId);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);


}
