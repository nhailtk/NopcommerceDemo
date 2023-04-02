package com.nopcommerce.pageUIs.user;

public class UserProductReviewsPageUI {
	public static String SUBMIT_REVIEW_BUTTON = "XPATH=//button[text()='Submit review']";
	public static String PRODUCT_REVIEW_TITLE = "XPATH=//div[@class='review-title']/strong[contains(text(),'%s')]";
	public static String PRODUCT_REVIEW_CONTENT= "XPATH=//div[@class='review-content']//div[contains(text(),'%s')]";
	public static String ADD_PRODUCT_REVIEW_CONTENT= "XPATH=//textarea[@id='AddProductReview_ReviewText']";
}
