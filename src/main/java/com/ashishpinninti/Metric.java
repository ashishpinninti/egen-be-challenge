package com.ashishpinninti;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("metrics")
class Metric {
	@Id
	private ObjectId id;
	private String timeStamp;
	private int value;

	public Metric() {
	}

	public Metric(ObjectId id, String timeStamp, int value) {
		super();
		this.id = id;
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metric other = (Metric) obj;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Metric [id=" + id + ", timeStamp=" + timeStamp + ", value="
				+ value + "]";
	}

}
