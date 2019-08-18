/**
 * 
 */
package com.tbone.limitservice.bean;

/**
 * @author Gaurav Kumar
 *
 */
public class LimitConfiguration {

	private int maximum;
	private int minimum;

	public LimitConfiguration(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public LimitConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

}
