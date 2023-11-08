package service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckService extends AbstrackBaseService {

	private static final Logger LOGGER = Logger.getLogger(CheckService.class.getName());
	private static final String CLASSNAME = CheckService.class.getName();
	private Client client;
	private MyResponse response;
	

	public CheckService(Client client, CheckType checkType) {
		super(checkType);
		this.client = client;
	}

	public Request buildRequest(Address address, String param1, String param2) {
		return new Request(address, param1, param2);
	}

	@Override
	public boolean isLocalTestEnvironment() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getServiceProiderName() {
		return "My service Provider name";
	}

	@Override
	public boolean doCall() throws Exception {
		final String methodName = "doCall";

		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.logp(Level.FINER, CLASSNAME, methodName, "Calling xy: {0}", prepareLogData());
		}

		Request request = buildRequest(getAddressToCheck(), "param1", "param2");

		try {
			this.response = client.execute(request);
			
		} catch (Exception e) {
			throw new MyCustomException(e);
		}

		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.logp(Level.FINER, CLASSNAME, methodName, "xy responded: {0} | {1} | {2} | {3} | {4} | {5} | {6}", prepareLogData());
		}
		
		if(response == null) {
			return false;
		} else {
			// weitere Prüung der Response dann
			return true;
		}

	}

	private Object[] prepareLogData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
