package verita;

public interface BaseService {
	
	public enum TestType {
		LOCAL,
		TEST_REMOTE_SERVICE,
		NONE
	}
	
	public abstract boolean call() throws Exception;
	
	public abstract boolean isLocalTestEnvironment() throws Exception; 
	
	public abstract String getServiceProiderName();
	
	public abstract void setAddressToCheck(Address address);

}
