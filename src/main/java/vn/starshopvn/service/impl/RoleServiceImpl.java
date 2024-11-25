package vn.starshopvn.service.impl;

import vn.starshopvn.dao.RoleDAO;
import vn.starshopvn.dao.impl.RoleDAOImpl;
import vn.starshopvn.entity.Role;
import vn.starshopvn.service.RoleService;

public class RoleServiceImpl implements RoleService{

	RoleDAO rDAO = new RoleDAOImpl();
	
	@Override
	public Role findById(int id) {
		return rDAO.findById(id);
	}

}
