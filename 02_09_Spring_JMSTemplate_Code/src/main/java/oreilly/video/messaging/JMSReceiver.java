package oreilly.video.messaging;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class JMSReceiver {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("app-context.xml");
		
		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
		System.out.println("Waiting on messages");

		//String body = (String)jmsTemplate.receiveAndConvert();
		//System.out.println(body);
		
		//TextMessage msg = (TextMessage)jmsTemplate.receive();
		//TextMessage msg = (TextMessage)jmsTemplate.receiveSelected("Trader = 'Mark'");
		TextMessage msg = (TextMessage)jmsTemplate.receiveSelected("Trader = 'Mary'");
		System.out.println(msg.getText() + ", Trader = " + msg.getStringProperty("Trader"));
		
		System.exit(0);
	}

}
