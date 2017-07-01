package oreilly.video.messaging;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSMetaData {

	public static void main(String[] args) throws Exception {

		Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
		
		ConnectionMetaData metadata = connection.getMetaData();

		System.out.println(" ");
		System.out.println("JMS Version: " + metadata.getJMSVersion());
		System.out.println("JMS Provider: " + metadata.getJMSProviderName());
		System.out.println("JMS Provider Version: " + metadata.getProviderVersion());
		System.out.println("JMSX Properties Supported: ");
		Enumeration<?> e = metadata.getJMSXPropertyNames();
		while (e.hasMoreElements()) {
			System.out.println("   " + e.nextElement());
		}
		connection.close();
	}
}
