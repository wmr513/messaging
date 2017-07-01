package oreilly.video.messaging;

import java.util.UUID;

import javax.jms.JMSContext;
import javax.jms.Queue;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class JMS2Sender {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queueReq = jmsContext.createQueue("EM_JMS2_TRADE_REQ.Q");			
			Queue queueResp = jmsContext.createQueue("EM_JMS2_TRADE_RESP.Q");			
			
			String uuid = UUID.randomUUID().toString();
			
			jmsContext
			.createProducer()
			.setJMSReplyTo(queueResp)
			.setProperty("uuid", uuid)
			.send(queueReq, "BUY AAPL 1000 SHARES");
			System.out.println("message sent");		
			
			String filter = "JMSCorrelationID = '" + uuid + "'";
			String conf = jmsContext.createConsumer(queueResp, filter).receiveBody(String.class);
			System.out.println("conf: " + conf);
		}
	}
}

