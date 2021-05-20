/*
 * AdministratorDashboardShowService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"totalNumberPublicTask", "totalNumberPrivateTask", "totalNumberFinishedTask", "totalNumberNonFinishedTask", 
			"averageTaskWorkload", "minTaskWorkload", "maxTaskWorkload", "deviationTaskWorkload",
			"averageTaskExecutionPeriods", "minTaskExecutionPeriods", "maxTaskExecutionPeriods", "deviationTaskExecutionPeriods");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Double totalNumberPublicTask;
		Double totalNumberPrivateTask;
		Double totalNumberFinishedTask;
		Double totalNumberNonFinishedTask;
		Double averageTaskWorkload;
		Double minTaskWorkload;
		Double maxTaskWorkload;
		Double deviationTaskWorkload;
		Double averageTaskExecutionPeriods;
		Double minTaskExecutionPeriods;
		Double maxTaskExecutionPeriods;
		Double deviationTaskExecutionPeriods;

		totalNumberPublicTask = this.repository.findManyPublic();
		totalNumberPrivateTask = this.repository.findManyPrivate();
		totalNumberFinishedTask = this.repository.findManyFinished();
		totalNumberNonFinishedTask = this.repository.findManyNonFinished();
		averageTaskWorkload = this.repository.findAverageTaskWorkload();
		minTaskWorkload = this.repository.findMinTaskWorkload();
		maxTaskWorkload = this.repository.findMaxTaskWorkload();
		deviationTaskWorkload = this.repository.findDeviationTaskWorkload();
		averageTaskExecutionPeriods = this.repository.findAverageTaskExecutionPeriods();
		minTaskExecutionPeriods = this.repository.findMinTaskExecutionPeriods();
		maxTaskExecutionPeriods = this.repository.findMaxTaskExecutionPeriods();
		deviationTaskExecutionPeriods = this.repository.findDeviationTaskExecutionPeriods();

		result = new Dashboard();
		result.setTotalNumberPublicTask(totalNumberPublicTask);
		result.setTotalNumberNonFinishedTask(totalNumberNonFinishedTask);
		result.setTotalNumberFinishedTask(totalNumberFinishedTask);
		result.setTotalNumberPrivateTask(totalNumberPrivateTask);
		result.setAverageTaskWorkload(averageTaskWorkload);
		result.setMaxTaskWorkload(maxTaskWorkload);
		result.setMinTaskWorkload(minTaskWorkload);
		result.setDeviationTaskWorkload(deviationTaskWorkload);
		result.setAverageTaskExecutionPeriods(averageTaskExecutionPeriods);
		result.setMinTaskExecutionPeriods(minTaskExecutionPeriods);
		result.setMaxTaskExecutionPeriods(maxTaskExecutionPeriods);
		result.setDeviationTaskExecutionPeriods(deviationTaskExecutionPeriods);
		


		return result;
	}

}
