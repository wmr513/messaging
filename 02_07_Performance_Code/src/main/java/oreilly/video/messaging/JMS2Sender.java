package oreilly.video.messaging;

import java.util.concurrent.CountDownLatch;

import javax.jms.CompletionListener;
import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

import com.sun.messaging.ConnectionFactory;

public class JMS2Sender {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();		
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");

			CountDownLatch latch = new CountDownLatch(1);
			MyCompletionListener cl = new MyCompletionListener(latch);
			
			long startTime = new Long(new java.util.Date().getTime());

			for (int i = 0; i < 1000; i++) {
				String body = "Message " + (i + 1);
				System.out.println("Sending Message: " + body);
				jmsContext.createProducer()
				.setAsync(cl)
				.setDeliveryMode(DeliveryMode.PERSISTENT)
				//.setDeliveryMode(DeliveryMode.NON_PERSISTENT)
				.setProperty("startTime", startTime)
				.setProperty("count", (i + 1))
				.send(queue, body);
			}

			latch.await();
			long endTime = new Long(new java.util.Date().getTime());
			System.out.println("\nElapsed Time: " + (endTime - startTime));

		} 
	}	
}

class MyCompletionListener implements CompletionListener {

	public CountDownLatch latch = null;

	public MyCompletionListener(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void onCompletion(Message message) {
		latch.countDown();
		//System.out.println("message acknowledged by server");
	}
	
	@Override
	public void onException(Message msg, Exception e) {
		latch.countDown();
		System.out.println("unable to send message: " + e);
	}

}






































