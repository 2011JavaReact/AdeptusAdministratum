package com.revature.karl.test;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.karl.model.Planet;
import com.revature.karl.service.PlanetService;

public class PlanetHttp {

	static boolean beforeTest = false;
	static PlanetService planetService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		beforeTest = true;
		planetService = new PlanetService();
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
	public void PlanetPOST() {
		Planet planet = new Planet("Tatooine", "Tuskens", 50, 2);
		Planet returnedPlanet = planetService.insertPlanet(planet);
		int deleteID = returnedPlanet.getId();
		planetService.deletePlanet(deleteID);
		assertTrue(planet.equals(returnedPlanet));
	}

	@Test
	public void PlanetPUT() {
		Planet planetPrime = new Planet("Tatooine", "Tuskens", 50, 2);
		Planet planetDelta = new Planet("Irk", "Irkens", 500, 2);
		Planet postedPlanet = planetService.insertPlanet(planetPrime);
		int deleteID = postedPlanet.getId();
		Planet updatedPlanet = planetService.updatePlanet(deleteID, planetDelta);
		planetService.deletePlanet(deleteID);
		assertTrue(planetDelta.equals(updatedPlanet));
	}

	@Test
	public void PlanetDELETE() {
		int planetSizePre = planetService.getAllPlanets().size();
		Planet planet = new Planet("Tatooine", "Tuskens", 50, 2);
		Planet postedPlanet = planetService.insertPlanet(planet);
		planetService.deletePlanet(postedPlanet.getId());
		int planetSizePost = planetService.getAllPlanets().size();
		assertTrue(planetSizePre == planetSizePost);
	}

}
