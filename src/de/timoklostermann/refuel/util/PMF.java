package de.timoklostermann.refuel.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;



public class PMF {
	
	/**
	 * The {@link PersistanceManagerFactory} instance.
	 */
	private static final PersistenceManagerFactory INSTANCE = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	/**
	 * Private Constructor. No Instance should be created.
	 */
	private PMF() {}
	
	/**
	 * Getting the {@link PersistenceManagerFactory} instance.
	 * @return {@link PersistenceManagerFactory}
	 */
	public static PersistenceManagerFactory get() {
		return INSTANCE;
	}
}
