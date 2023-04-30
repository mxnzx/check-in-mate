package com.ssafy.enjoytrip.attr.model;


// // 관광지 정보 객체
public class AttrInfoDto {
	private int sidoCode;
	private int gugunCode;
	private int contentId;
	private String title;
	private String address;
	private String imgUrl;
	private double latitude;
	private double longitude;

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "attrInfo [sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", contentId=" + contentId + ", title="
				+ title + ", address=" + address + ", imgUrl=" + imgUrl + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
}
