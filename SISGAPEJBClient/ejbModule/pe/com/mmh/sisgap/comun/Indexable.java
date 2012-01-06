package pe.com.mmh.sisgap.comun;

/**
 * Interface for an object with a database primary key ID
 * <p>
 * 
 * @author Atanas Roussev
 */
public interface Indexable {

	/**
	 * returns the database id
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * @param id
	 *            the id to set
	 */
	void setId(Integer id);

}