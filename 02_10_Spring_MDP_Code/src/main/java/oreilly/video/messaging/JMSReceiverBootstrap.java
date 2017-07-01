package oreilly.video.messaging;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMSReceiverBootstrap {

	public void startReceivers() {
		try {
			new ClassPathXmlApplicationContext("app-context.xml");
	    	System.out.println("MDP Receivers Started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMSReceiverBootstrap().startReceivers();
	    }}.start();
	}
}













//alternatives
//BrokerService broker = BrokerFactory.createBroker(new URI("broker:tcp://localhost:61888"));
//broker.setBrokerName("embedded1");
//broker.setPersistent(false);

//BrokerService broker = BrokerFactory.createBroker(new URI("xbean:activemq.xml"));

