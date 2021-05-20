
/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Task t where t.isPublic = true")
	Double findManyPublic();

	@Query("select count(t) from Task t where t.isPublic = false")
	Double findManyPrivate();
	
	@Query("select count(t) from Task t where t.finalMoment < current_timestamp()")
	Double findManyFinished();	
	
	@Query("select count(t) from Task t where t.finalMoment > current_timestamp()")
	Double findManyNonFinished();	
	
	@Query("select avg(t.workload) from Task t")
	Double findAverageTaskWorkload();	
	
	@Query("select max(t.workload) from Task t")
	Double findMaxTaskWorkload();	
	
	@Query("select min(t.workload) from Task t")
	Double findMinTaskWorkload();	
	
	@Query("select stddev(t.workload) from Task t")
	Double findDeviationTaskWorkload();	
	
	@Query("select avg(datediff(t.finalMoment, t.initialMoment)) from Task t")
	Double findAverageTaskExecutionPeriods();	
	
	@Query("select max(datediff(t.finalMoment, t.initialMoment)) from Task t")
	Double findMaxTaskExecutionPeriods();	
	
	@Query("select min(datediff(t.finalMoment, t.initialMoment)) from Task t")
	Double findMinTaskExecutionPeriods();	
	
	@Query("select stddev(datediff(t.finalMoment, t.initialMoment)) from Task t")
	Double findDeviationTaskExecutionPeriods();	


}
	
