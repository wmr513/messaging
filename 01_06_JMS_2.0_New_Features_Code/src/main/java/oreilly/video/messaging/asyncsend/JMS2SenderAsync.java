package oreilly.video.messaging.asyncsend;

import java.util.concurrent.CountDownLatch;

import javax.jms.CompletionListener;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import javax.management.RuntimeErrorException;

import com.sun.messaging.ConnectionFactory;
 
public class JMS2SenderAsync {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();		
		try (JMSContext jmsContext = cf.createContext();) {
			Queue queue = jmsContext.createQueue("EM_JMS2_TRADE.Q");

			CountDownLatch latch = new CountDownLatch(1);
			MyCompletionListener cl = new MyCompletionListener(latch);
			
			jmsContext.createProducer().setAsync(cl).send(queue, "Test Message");
			System.out.println("Message sent");
		
			for (int i=0;i<5;i++) {
				System.out.println("processing...");
				Thread.sleep(1000);
			}
			
			latch.await();
			
			System.out.println("end processing");
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
		System.out.println("message acknowledged by server");
	}
	
	@Override
	public void onException(Message msg, Exception e) {
		latch.countDown();
		System.out.println("unable to send message: " + e);
	}

}


















