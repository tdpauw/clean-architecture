package io.thinkinglabs;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 * @author @tdpauw
 */
public class JPATransactor implements Transactor {
	private final EntityManager entityManager;

	public JPATransactor(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public void perform(UnitOfWork unitOfWork) throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			unitOfWork.work();
			transaction.commit();
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public Object performQuery(QueryUnitOfWork queryUnitOfWork)
			throws Exception {
		Object result = null;
		try {
			result = queryUnitOfWork.query();
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
