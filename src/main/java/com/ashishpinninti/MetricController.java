package com.ashishpinninti;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/metric")
public class MetricController {

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Metric registerMetric(@RequestBody Metric bean) {
		MicroserviceDemoApplication.getDatastore().save(bean);

		// Apply Rules
		// register rules
		RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
		rulesEngine.registerRule(new UnderWeightRule(bean));
		rulesEngine.registerRule(new OverWeightRule(bean));
		// fire rules
		rulesEngine.fireRules();

		return bean;
	}

	@ResponseBody
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public List<Metric> retrieveAll() {
		Query<Metric> query = MicroserviceDemoApplication.getDatastore()
				.createQuery(Metric.class);
		final List<Metric> metrics = query.asList();
		return metrics;
	}

	@ResponseBody
	@RequestMapping(value = "/readByTimeRange/{t1}/{t2}", method = RequestMethod.GET)
	public List<Metric> readByTimeRange(@PathVariable("t1") String t1,
			@PathVariable("t2") String t2) {
		Query<Metric> query = MicroserviceDemoApplication.getDatastore()
				.createQuery(Metric.class).field("timeStamp")
				.greaterThanOrEq(t1).field("timeStamp").lessThanOrEq(t2);
		final List<Metric> metrics = query.asList();

		return metrics;

	}

}
