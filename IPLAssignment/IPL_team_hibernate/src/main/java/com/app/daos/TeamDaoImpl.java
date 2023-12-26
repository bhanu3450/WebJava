package com.app.daos;

import com.app.pojos.Teams;
import org.hibernate.*;
import static com.app.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;
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

	@Override
	public List<Teams> getAllTeamIdAndAbbrevaitionById() {
		 List<Teams> teams = null;
         Session session = getFactory().getCurrentSession();
         Transaction tx = session.beginTransaction();
         try {
        	 String jpql = "select new com.app.pojos.Teams(teamId,abbreviation) from Teams t";
        	 teams = session.createQuery(jpql, Teams.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
			tx.rollback();
			throw e;
		}      
		return teams;
	}

	@Override
	public Teams getTeamById(Integer id) {
		Teams team = null;
		Session session = getFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
	    try {
	    	// in case of valid id , team : PERSISTENT , part of L1 cache , exists in DB
	    	 team = session.get(Teams.class, id);
	    	tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		return team;// team : DETACHED
	}

	@Override
	public List<Teams> displayTeamsByMaxAgeAndWicketsTaken(int maxAge, int wicketsTaken) {
		List<Teams> list = null;
		Session session = getFactory().getCurrentSession();
		 Transaction tx = session.beginTransaction();
		    try {
		    	// in case of valid id , team : PERSISTENT , part of L1 cache , exists in DB
		    	String jpql = "select t from Teams t where t.maxAge < :max and t.wicketsTaken > :wickets";
		    	 list = session.createQuery(jpql, Teams.class)
		    			 .setParameter( "max" , maxAge)
		    			 .setParameter("wickets", wicketsTaken )
		    			 .getResultList();
		    	tx.commit();
			} catch (RuntimeException e) {
				if(tx != null)
					tx.rollback();
				throw e;
			}       
		return list;// team : DETACHED
	}

	@Override
	public String updateMaxAgeAndBattingAvg(String name, int maxage, double battingAvg) {
		String mesg = "updation failed!!!!";
		String jpql = "select t from Teams t where name=:naam";
	    Teams team = null;
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			team = session.createQuery(jpql, Teams.class).setParameter("naam", name).getSingleResult();
			// => team : PERSISTENT
			team.setMaxAge(team.getMaxAge() + maxage);;// modifying state of the persistent entity
			team.setBattingAvg(team.getBattingAvg() + battingAvg);
			// session.evict(team);
			// team : detached from L1 cache
			tx.commit();// session.flush() --> auto dirty checking --> DML : update
			// --> session.close() --> L1 cache is destroyed , db cn returns to the pool
			mesg = "Updated sal of " + team.getName();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		team.setMaxAge(team.getMaxAge() + maxage);;// modifying state of DETACHED entity
		return mesg;
	}

	@Override
	public String deleteTeamDetailsById(Integer teamId) {
		String mesg = "emp deletion failed !!!!";
		Teams team = null;
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			// get persistent team by it's id
			team = session.get(Teams.class, teamId);
			if (team != null) {
				// team : persistent
				// mark the persistent team for removal
				session.delete(team);
				// team : REMOVED
				mesg="deleted team details of " + team.getName();
			}
			tx.commit();// session.flush --> dirty checking ---> 
			//DML : delete , record is deleted -->
			// session.close() --> L1 cache is destroyed 
			//(entity removed from cache) : team :  TRANSIENT , connection returns to pool
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return mesg;
	} // team marked for GC
     
}
