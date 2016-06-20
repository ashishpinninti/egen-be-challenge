package com.ashishpinninti;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/alert")
public class AlertController {

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Alert registerAlert(@RequestBody Alert bean) {
		MicroserviceDemoApplication.getDatastore().save(bean);
		return bean;
	}

	@ResponseBody
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public List<Alert> retrieveAll() {
		Query<Alert> query = MicroserviceDemoApplication.getDatastore()
				.createQuery(Alert.class);
		final List<Alert> alerts = query.asList();
		return alerts;
	}

	@ResponseBody
	@RequestMapping(value = "/readByTimeRange/{t1}/{t2}", method = RequestMethod.GET)
	public List<Alert> readByTimeRange(@PathVariable("t1") String t1,
			@PathVariable("t2") String t2) {
		Query<Alert> query = MicroserviceDemoApplication.getDatastore()
				.createQuery(Alert.class).field("timeStamp")
				.greaterThanOrEq(t1).field("timeStamp").lessThanOrEq(t2);
		final List<Alert> alerts = query.asList();
		return alerts;
	}

}
