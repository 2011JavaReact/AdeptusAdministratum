package com.revature.karl.service;

import java.util.ArrayList;

import com.revature.karl.dao.EmpireDAO;
import com.revature.karl.model.Planet;

public class EmpireService {

	private EmpireDAO empireDAO;

	public EmpireService() {
		this.empireDAO = new EmpireDAO();
	}

	public EmpireService(EmpireDAO empireDAO) {
		this.empireDAO = empireDAO;
	}

	public ArrayList<Planet>  getEmpire(String inhabitants) {
		return empireDAO.getEmpire(inhabitants);
	}
}
