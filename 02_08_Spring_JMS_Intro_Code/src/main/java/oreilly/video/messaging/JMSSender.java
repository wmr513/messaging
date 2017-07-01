package oreilly.video.messaging;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("app-context.xml");
		System.exit(0);
	}
}
