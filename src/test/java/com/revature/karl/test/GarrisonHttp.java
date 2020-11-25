package com.revature.karl.test;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.karl.model.Garrison;
import com.revature.karl.service.GarrisonService;

public class GarrisonHttp {

	static boolean beforeTest = false;
	static GarrisonService garrisonService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		beforeTest = true;
		garrisonService = new GarrisonService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestTest() {
		assertTrue(true);
	}

	@Test
	public void BeforeTest() {
		assertTrue(beforeTest);
	}

	@Test
	public void GarrisonPOST() {
		Garrison garrison = new Garrison("A bunch of angry ferrets", 12);
		Garrison returnedGarrison = garrisonService.insertGarrison(garrison);
		int deleteID = returnedGarrison.getId();
		garrisonService.deleteGarrison(deleteID);
		assertTrue(garrison.equals(returnedGarrison));
	}

	@Test
	public void GarrisonPUT() {
		Garrison garrisonPrime = new Garrison("A bunch of angry ferrets", 12);
		Garrison garrisonDelta = new Garrison("A cup of hotdog water", 1);
		Garrison postedGarrison = garrisonService.insertGarrison(garrisonPrime);
		int deleteID = postedGarrison.getId();
		Garrison updatedGarrison = garrisonService.updateGarrison(deleteID, garrisonDelta);
		garrisonService.deleteGarrison(deleteID);
		assertTrue(garrisonDelta.equals(updatedGarrison));
	}
	
	@Test
	public void GarrisonDELETE() {
		int garrisonSizePre = garrisonService.getAllGarrisons().size();
		Garrison garrison = new Garrison("A bunch of angry ferrets", 12);
		Garrison postedGarrison = garrisonService.insertGarrison(garrison);
		garrisonService.deleteGarrison(postedGarrison.getId());
		int garrisonSizePost = garrisonService.getAllGarrisons().size();
		assertTrue(garrisonSizePre == garrisonSizePost);
	}
}
