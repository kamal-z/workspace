package verita;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.StopWatch;



public abstract class AbstrackBaseService implements BaseService {
	
	
	private static final Logger logger = Logger.getLogger(AbstrackBaseService.class.getName());
	private static final String CLASSNAME = AbstrackBaseService.class.getName();
	
	public enum CheckType{
		
		ADDRESS(AddressCheckConstants.CHECK_TYPE_ADDRESS),
		CREDIT(AddressCheckConstants.CHECK_TYPE_CREDIT),
		CREDIT_ADDRESS(AddressCheckConstants.CHECK_TYPE_CREDIT_ADDRESS),
		NONE(AddressCheckConstants.CHECK_TYPE_NONE);
		
		private String requestId;
		
		private CheckType(String requestId) {
			this.requestId = requestId;
		}
		
		public String getRequestTypeId() {
			return requestId;
		}
		
		public static CheckType getCheckTypeFromString(String pCheckType) throws Exception {
			for(CheckType checkType : values()) {
				if(checkType.getRequestTypeId().equals(pCheckType)) {
					return checkType;
				}
			}
			
			throw new Exception("Unknown check type received: " + pCheckType);
		}

	}
	
	
	private Address addressToCheck; 
	private CheckType checkType;
	
	public AbstrackBaseService(CheckType checkType) {
		this.checkType = checkType;
	}
	
	@Override
	public final boolean call() throws Exception {
		
		StopWatch stopWatch = new StopWatch();
		try {
			logAddressToCheck();
			validateAddressCheckDataBean();
			stopWatch.start();
			return doCall();
		} finally {
			stopWatch.stop();
			if(logger.isLoggable(Level.FINE)) {
				logger.log(Level.FINE, " ClassName: {0}, CheckTye: {1}, Performance: Request duration was {2}ms from {3}", 
						new Object[]{ CLASSNAME, getCheckType().requestId, stopWatch.toString(), this.getServiceProiderName()});
			}
		} 
	}

	
	private void logAddressToCheck() {
		final String METHODNAME = "logAddressToCheck";
		// log address implementation
	}
	private void validateAddressCheckDataBean() throws Exception {
		final String METHODNAME = "validateAddressCheckDataBean";
		
		if(getAddressToCheck() == null) {
			throw new Exception("your msg");
		}
		
		if (getCheckType() == null) {
			throw new Exception("your msg");
		}
		 
		// further validation 
	}

	
	public Address getAddressToCheck() {
		return addressToCheck;
	}

	public abstract boolean doCall() throws Exception;
	
	public void setAddressToCheck(Address addressToCheck) {
		this.addressToCheck = addressToCheck;
	}


	public CheckType getCheckType() {
		return checkType;
	}


	public void setCheckType(CheckType checkType) {
		this.checkType = checkType;
	}
 
}
