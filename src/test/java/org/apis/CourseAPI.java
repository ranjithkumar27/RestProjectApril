package org.apis;

import java.util.Iterator;

import org.payloads.PayloadCourseAPI;
import org.reusableMethods.BaseSteps;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class CourseAPI {

	// Proceeding without API resquest

	public static void main(String[] args) {

		JsonPath dummyCourseResponse = BaseSteps.jsonPath(PayloadCourseAPI.courseApiDummyResponse());

		// 1. Print No of courses returned by API

		int CoursesSize = dummyCourseResponse.getInt("courses.size()");

		System.out.println("1. Print No of courses returned by API ====>  " + CoursesSize);

		// 2. Print Purchase Amount

		int purchaseAmount = dummyCourseResponse.getInt("dashboard.purchaseAmount");

		System.out.println("2. Print Purchase Amount ====>" + purchaseAmount);

		// 3. Print Title of the first course

		String firstCourseTitle = dummyCourseResponse.getString("courses[0].title");

		System.out.println("3. Print Title of the first course =====>  " + firstCourseTitle);

		// 4. Print All course titles and their respective Prices

		for (int i = 0; i < CoursesSize; i++) {
			String allCoursesTitle = dummyCourseResponse.getString("courses[" + i + "].title");

			System.out.println("4. Print All course titles ==> " + allCoursesTitle);

			int allCoursesprices = dummyCourseResponse.getInt("courses[" + i + "].price");

			System.out.println("4. Print All course  Prices ===> " + allCoursesprices);
		}

		// 5. Print no of copies sold by RPA Course

		for (int i = 0; i < CoursesSize; i++) {

			String coursesTitle = dummyCourseResponse.getString("courses[" + i + "].title");

			if (coursesTitle.equalsIgnoreCase("RPA")) {

				int copiesSoldByRPA = dummyCourseResponse.getInt("courses[" + i + "].copies");

				System.out.println("5. Print no of copies sold by RPA Course ===> " + copiesSoldByRPA);

			}

		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		int totalAmount = 0;
		for (int i = 0; i < CoursesSize; i++) {

			int coursesprice = dummyCourseResponse.getInt("courses[" + i + "].price");

			int coursescopies = dummyCourseResponse.getInt("courses[" + i + "].copies");

			int sumOfcopiesandPrices = coursesprice * coursescopies;

			totalAmount = totalAmount + sumOfcopiesandPrices;

		}

		System.out.println("Summ of all course amount===>  " + totalAmount);

		int totalPurchaseAmt = dummyCourseResponse.getInt("dashboard.purchaseAmount");

		Assert.assertEquals(totalAmount, totalPurchaseAmt);

	}

}
