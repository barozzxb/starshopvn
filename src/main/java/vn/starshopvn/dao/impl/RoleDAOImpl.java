package vn.starshopvn.dao.impl;

import jakarta.persistence.EntityManager;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.RoleDAO;
import vn.starshopvn.entity.Role;

public class RoleDAOImpl implements RoleDAO{

	@Override
	public Role findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Role role = enma.find(Role.class, id);
		return role;
	}

}
