package com.revature.karl.service;

import java.util.ArrayList;

import com.revature.karl.dao.GarrisonDAO;
import com.revature.karl.model.Garrison;

public class GarrisonService {

	private GarrisonDAO garrisonDAO;

	public GarrisonService() {
		this.garrisonDAO = new GarrisonDAO();
	}

	public GarrisonService(GarrisonDAO garrisonDAO) {
		this.garrisonDAO = garrisonDAO;
	}

	public ArrayList<Garrison> getAllGarrisons() {
		return garrisonDAO.getAllGarrisons();
	}

	public Garrison getGarrison(int idParam) {
		return garrisonDAO.getGarrison(idParam);
	}

	public Garrison insertGarrison(Garrison garrison) {
		return garrisonDAO.insertGarrison(garrison);
	}

	public Garrison updateGarrison(int id, Garrison garrison) {
		return garrisonDAO.updateGarrison(id, garrison);
	}

	public void deleteGarrison(int id) {
		garrisonDAO.deleteGarrison(id);
	}
}
