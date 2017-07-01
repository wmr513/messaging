package oreilly.video.messaging;

public class TradeProcessor {

	public void processTrade(String trade) {
		try {
			System.out.println("Processing Trade " + trade + "\n");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
