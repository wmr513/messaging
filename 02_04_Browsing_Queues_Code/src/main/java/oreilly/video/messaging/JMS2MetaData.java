package oreilly.video.messaging;

import java.util.Enumeration;

import javax.jms.ConnectionMetaData;
import javax.jms.JMSContext;

import com.sun.messaging.ConnectionFactory;

public class JMS2MetaData {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			ConnectionMetaData metadata = jmsContext.getMetaData();
			System.out.println(" ");
			System.out.println("JMS Version: " + metadata.getJMSVersion());
			System.out.println("JMS Provider: " + metadata.getJMSProviderName());
			System.out.println("JMS Provider Version: " + metadata.getProviderVersion());
			System.out.println("JMSX Properties Supported: ");
			Enumeration<?> e = metadata.getJMSXPropertyNames();
			while (e.hasMoreElements()) {
				System.out.println("   " + e.nextElement());
			}
			
		}
	}
}






































