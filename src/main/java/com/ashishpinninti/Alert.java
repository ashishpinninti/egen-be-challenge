package com.ashishpinninti;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("alerts")
class Alert {
	@Id
	private ObjectId id;
	@Reference
	private Metric metric;
	private String message;
	private int base_weight;
	private String timeStamp;

	public Alert() {
	}

	public Alert(Metric metric, String message, int base_weight,
			String timeStamp) {
		super();
		this.metric = metric;
		this.message = message;
		this.base_weight = base_weight;
		this.timeStamp = timeStamp;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getBase_weight() {
		return base_weight;
	}

	public void setBase_weight(int base_weight) {
		this.base_weight = base_weight;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", metric=" + metric + ", message="
				+ message + ", base_weight=" + base_weight + "]";
	}

}
