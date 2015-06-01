package Interface;

public interface ADM {

	/**
	 * Return a boolean that is false if the object already exist in the database or if the request doesn't succeed
	 * @return res
	 */
	public boolean create();
	
	/**
	 * Return a boolean that is false if the object don't exist in the database or if the request doesn't succeed
	 * @return res
	 */
	public boolean update();
	
	/**
	  Return a boolean that is false if the object don't exist in the database or if the request doesn't succeed
	 * @return res
	 */
	public boolean delete();
	
}
