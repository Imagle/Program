package practice.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;;

public class TestLog4j2 {

	public static Logger logger  = LogManager.getLogger(TestLog4j2.class.getName());
	public static void main(String[] args) {
		logger.trace("test");
		logger.error("error");
		
	}

}