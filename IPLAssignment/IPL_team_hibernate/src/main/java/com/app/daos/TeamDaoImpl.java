package com.app.daos;

import com.app.pojos.Teams;
import org.hibernate.*;
import static com.app.utils.HibernateUtils.getFactory;

import java.io.Serializable;
public class TeamDaoImpl implements TeamDao {

	@Override
	public String addNewTeam(Teams newTeam) {
		// 1. Get Session from SF
		Session session = getFactory().getCurrentSession();
		String msg = "Adding team failed";
		// 2. Begin a tx
		 Transaction tx = session.beginTransaction();
		// 3. try -save team details, commit, 
		// catch : runtime exc : rollback tx, throw e
		try {
      Serializable teamId = session.save(newTeam);
		tx.commit();
		msg = "Team added Succesfuully with teamID " + teamId;
		} catch (RuntimeException e) {
			if(tx != null)
			  tx.rollback();
			e.printStackTrace();
			throw e;
		}
		return msg;
	}
     
}
