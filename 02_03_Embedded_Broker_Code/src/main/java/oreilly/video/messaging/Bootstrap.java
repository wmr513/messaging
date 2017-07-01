package oreilly.video.messaging;

import org.apache.activemq.broker.BrokerService;

public class Bootstrap {

	public void startBroker() {
		try {
			BrokerService broker = new BrokerService();
			broker.addConnector("tcp://localhost:61888");
			broker.setBrokerName("embedded1");
			broker.setPersistent(false);
			broker.start();
			System.out.println("Embedded broker started");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new Bootstrap().startBroker();
	    }}.start();
	}
}













//alternatives
//BrokerService broker = BrokerFactory.createBroker(new URI("broker:tcp://localhost:61888"));
//broker.setBrokerName("embedded1");
//broker.setPersistent(false);

//BrokerService broker = BrokerFactory.createBroker(new URI("xbean:activemq.xml"));

