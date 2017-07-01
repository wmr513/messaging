package oreilly.video.messaging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver implements MessageListener {
	
	private static String targetFile = "/Users/wmr/Development/Workspaces/EMVIDEO/EM_2_05_IMAGES_DOCS/targetImageDir/pint.jpg";

    private OutputStream os;
    
    public void onMessage(Message message) {
		try {
			
//			String filename = ((TextMessage) message).getText();
//			System.out.println("Message Received: " + filename);
//			writeFileToTarget(readFileFromTemp(filename));
//			Runtime.getRuntime().exec("open -a Preview " + targetFile);
			
//			BytesMessage msg = (BytesMessage) message;
//			System.out.println("Message Received");
//			byte[] bytes = new byte[new Long(msg.getBodyLength()).intValue()];
//			msg.readBytes(bytes);
//			writeFileToTarget(bytes);
//			Runtime.getRuntime().exec("open -a Preview " + targetFile);
			
			if (message.propertyExists("sequenceMarker")) {
				String marker = message.getStringProperty("sequenceMarker");
				System.out.println("Received Marker: " + marker);

				if (marker.equals("START")) {
					os = new FileOutputStream(targetFile);
				}

				if (marker.equals("END")) {
					os.close();
					Runtime.getRuntime().exec("open -a Preview " + targetFile);
				}
			} else {
				BytesMessage msg = (BytesMessage) message;
				System.out.println("Received Message");
				byte[] bytes = new byte[new Long(msg.getBodyLength()).intValue()];
				msg.readBytes(bytes);
				os.write(bytes);
			}
						
		} catch (Exception up) {
			up.printStackTrace();
		}
		
	}

//    public byte[] readFileFromTemp(String tempFilename) throws Exception {
//		File file = new File(tempFilename);
//		try (InputStream is = new FileInputStream(file);) {
//			byte[] bytes = new byte[(int) file.length()];
//			is.read(bytes);
//			//file.delete(); commented to view tempdir
//			return bytes;
//		}
//	}
    
    public void writeFileToTarget(byte[] bytes) throws Exception {
		try (OutputStream os = new FileOutputStream(targetFile);) {
			os.write(bytes);
		}
	}
    
	public JMSReceiver() {
		try {
			Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("EM_IMAGE.Q");
			MessageConsumer receiver = session.createConsumer(queue);
			receiver.setMessageListener(this);
			os = new FileOutputStream(targetFile);
			System.out.println("Waiting for messages...");
		} catch (Exception up) {
			up.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {		
	    new Thread() { 
	    	public void run() { 
	    		new JMSReceiver();
	    }}.start();
	}

}











