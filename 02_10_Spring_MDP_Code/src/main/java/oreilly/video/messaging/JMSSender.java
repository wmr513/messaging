package oreilly.video.messaging;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

public class JMSSender {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("app-context.xml");
		JmsTemplate jmsTemplate = 
			(JmsTemplate)ctx.getBean("jmsTemplate");

		MessagePostProcessor mpp = new MessagePostProcessor() {
			public Message postProcessMessage(Message msg) throws JMSException {
	    		msg.setStringProperty("Trader", "Mary");
	    		//msg.setStringProperty("Trader", "Mark");
				return msg;
			}
		};
		
		jmsTemplate.convertAndSend((Object)"BUY AAPL 3000 SHARES", mpp);		
		System.out.println("Message sent");		
		System.exit(0);
	}
}
