package de.timoklostermann.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;



public class PersistanceManager {
	
	/**
	 * The {@link PersistanceManagerFactory} instance.
	 */
	private static final PersistenceManagerFactory INSTANCE = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	/**
	 * Private Constructor. No Instance should be created.
	 */
	private PersistanceManager() {}
	
	/**
	 * Getting the {@link PersistenceManagerFactory} instance.
	 * @return {@link PersistenceManagerFactory}
	 */
	public static PersistenceManagerFactory getInstance() {
		return INSTANCE;
	}
}
