package oreilly.video.messaging;

import java.util.Enumeration;
import java.util.Random;

import javax.jms.DeliveryMode;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;

public class ConcurrentTest {

	JmsTemplate jms = null;
	String queue = "TRADE.SPRING.Q";
	
	public static void main(String[] args) throws Exception {
			ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("concurrent-app-context.xml");
			ConcurrentTest app = new ConcurrentTest();
			app.jms = (JmsTemplate)ctx.getBean("jmsTemplate");
			
			long startTime = new Long(new java.util.Date().getTime());
			app.run();
			long endTime = new Long(new java.util.Date().getTime());
			System.out.println("\nElapsed Time: " + (endTime - startTime));
			System.exit(0);
	}

	public void run() throws Exception {
		
		for (int i=0;i<60;i++) {
			long shares = ((long)((new Random().nextDouble()*4000)+1));
			String trade = "[" + (i+1) + "]: " + "BUY " + shares + " shares of AAPL";
			System.out.println("Placing Trade    " + trade);
			jms.convertAndSend(trade);
		}
		waitForAsyncToComplete();
	}
	
	
	private void waitForAsyncToComplete() throws Exception{
		boolean processing = true;
		while (processing) {
			processing = false;
			Thread.sleep(10);
			if (!queueDone(queue)) processing = true;
		}
	}
	
	private boolean queueDone(String queue) throws Exception {
		Object done = jms.browse(queue, new BrowserCallback() {
            public Object doInJms(Session session, QueueBrowser browser) {
            	try {
                	Enumeration e = browser.getEnumeration();
                	if (e.hasMoreElements()) {
                		return new Object();
                	} else {
                		return null;
                	}
            	} catch (Exception e) {
            		e.printStackTrace();
            		return null;
            	}
            }
        });
		return (done == null) ;
	}
}






