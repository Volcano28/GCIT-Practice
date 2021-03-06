package com.gcit.lms.domain;

import org.springframework.data.annotation.Id;

public class Publisher {
	
	@Id
	private long publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;
	/**
	 * @return the publisherId
	 */
	public long getPublisherId() {
		return publisherId;
	}
	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(long publisherId) {
		this.publisherId = publisherId;
	}
	/**
	 */
	public String getPublisherName() {
		return publisherName;
	}
	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	/**
	 * @return the publisherAddress
	 */
	public String getPublisherAddress() {
		return publisherAddress;
	}
	/**
	 * @param publisherAddress the publisherAddress to set
	 */
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	/**
	 * @return the publisherPhone
	 */
	public String getPublisherPhone() {
		return publisherPhone;
	}
	/**
	 * @param publisherPhone the publisherPhone to set
	 */
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	
}
