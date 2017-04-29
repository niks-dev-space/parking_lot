package com.gojek.entities;

public class Slot implements Comparable<Slot> {
	/*
	 * use number instead of string to track distance from entry
	 */
	private final Integer slotNumber;
	private Boolean isFree;
	private Car car;

	public Slot(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	public Boolean isFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Integer getSlotNumber() {
		return slotNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((slotNumber == null) ? 0 : slotNumber.hashCode());
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
		Slot other = (Slot) obj;
		if (slotNumber == null) {
			if (other.slotNumber != null)
				return false;
		} else if (!slotNumber.equals(other.slotNumber))
			return false;
		return true;
	}

	public int compareTo(Slot o) {
		return this.slotNumber.compareTo(o.getSlotNumber());
	}

}
