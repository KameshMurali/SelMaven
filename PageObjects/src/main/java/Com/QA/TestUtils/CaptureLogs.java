package Com.QA.TestUtils;


import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

public class CaptureLogs {
	public static Logger autoLogs(String TestName){
		Logger logger = Logger.getLogger(TestName);
		//DOMConfigurator.configure("log4j.xml");
		PropertyConfigurator.configure("log4j2.properties");
		return logger;
		
	}
}