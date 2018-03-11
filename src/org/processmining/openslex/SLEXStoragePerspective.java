package org.processmining.openslex;
/*
 * 
 */


// TODO: Auto-generated Javadoc
/**
 * The Interface SLEXStoragePerspective.
 *
 * @author <a href="mailto:e.gonzalez@tue.nl">Eduardo Gonzalez Lopez de Murillas</a>
 * @see <a href="https://www.win.tue.nl/~egonzale/projects/openslex/" target="_blank">OpenSLEX</a>
 */
public interface SLEXStoragePerspective extends SLEXStorage {

	/**
	 * Gets the perspectives.
	 *
	 * @return the perspectives
	 */
	public abstract SLEXPerspectiveResultSet getPerspectives();

	/**
	 * Gets the perspectives of collection.
	 *
	 * @param ec the ec
	 * @return the perspectives of collection
	 */
	public abstract SLEXPerspectiveResultSet getPerspectivesOfCollection(
			SLEXEventCollection ec);

	//public abstract SLEXTrace createTrace(int perspectiveId, String caseId);
	
	/**
	 * Creates the trace.
	 *
	 * @param traceId the trace id
	 * @param perspectiveId the perspective id
	 * @param caseId the case id
	 * @return the SLEX trace
	 */
	public abstract SLEXTrace createTrace(int traceId, int perspectiveId, String caseId);

	/**
	 * Creates the perspective.
	 *
	 * @param evCol the ev col
	 * @param name the name
	 * @return the SLEX perspective
	 */
	public abstract SLEXPerspective createPerspective(
			SLEXEventCollection evCol, String name);

	/**
	 * Clone trace.
	 *
	 * @param t the t
	 * @param newTraceId the new trace id
	 * @return the SLEX trace
	 */
	public abstract SLEXTrace cloneTrace(SLEXTrace t, int newTraceId);

	/**
	 * Gets the traces of perspective.
	 *
	 * @param slexPerspective the slex perspective
	 * @return the traces of perspective
	 */
	public abstract SLEXTraceResultSet getTracesOfPerspective(
			SLEXPerspective slexPerspective);

	/**
	 * Insert.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXPerspective p);

	/**
	 * Update.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXPerspective p);

	/**
	 * Removes the trace from perspective.
	 *
	 * @param slexPerspective the slex perspective
	 * @param t the t
	 * @return true, if successful
	 */
	public abstract boolean removeTraceFromPerspective(
			SLEXPerspective slexPerspective, SLEXTrace t);

	/**
	 * Adds the event to trace.
	 *
	 * @param traceId the trace id
	 * @param eventId the event id
	 * @return true, if successful
	 */
	public abstract boolean addEventToTrace(int traceId, int eventId);
	
	/**
	 * Adds the event to trace.
	 *
	 * @param slexTrace the slex trace
	 * @param e the e
	 * @return true, if successful
	 */
	public abstract boolean addEventToTrace(SLEXTrace slexTrace, SLEXEvent e);

	/**
	 * Insert.
	 *
	 * @param t the t
	 * @return true, if successful
	 */
	public abstract boolean insert(SLEXTrace t);

	/**
	 * Update.
	 *
	 * @param t the t
	 * @return true, if successful
	 */
	public abstract boolean update(SLEXTrace t);

	/**
	 * Gets the events of trace.
	 *
	 * @param slexTrace the slex trace
	 * @return the events of trace
	 */
	public abstract SLEXEventResultSet getEventsOfTrace(SLEXTrace slexTrace);

	/**
	 * Gets the number events of trace.
	 *
	 * @param slexTrace the slex trace
	 * @return the number events of trace
	 */
	public abstract int getNumberEventsOfTrace(SLEXTrace slexTrace);

	/**
	 * Gets the max trace id.
	 *
	 * @return the max trace id
	 */
	public abstract int getMaxTraceId();
	
}
