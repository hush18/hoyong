package com.team3.admin.faq.dto;

public class AdminFaqDto {
	private int faq_number;
	private String title;
	private String content;
	private String up_category;
	private String down_category;
	private int count;
	private int rnum;
	private String is_top_ten;
	
	public int getFaq_number() {
		return faq_number;
	}
	public void setFaq_number(int faq_number) {
		this.faq_number = faq_number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUp_category() {
		return up_category;
	}
	public void setUp_category(String up_category) {
		this.up_category = up_category;
	}
	public String getDown_category() {
		return down_category;
	}
	public void setDown_category(String down_category) {
		this.down_category = down_category;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getIs_top_ten() {
		return is_top_ten;
	}
	public void setIs_top_ten(String is_top_ten) {
		this.is_top_ten = is_top_ten;
	}
	
	@Override
	public String toString() {
		return "AdminFaqDto [faq_number=" + faq_number + ", title=" + title + ", content=" + content + ", up_category="
				+ up_category + ", down_category=" + down_category + ", count=" + count + ", rnum=" + rnum
				+ ", is_top_ten=" + is_top_ten + "]";
	}
}
