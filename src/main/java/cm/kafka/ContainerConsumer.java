package cm.kafka;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import com.google.gson.Gson;

public class ContainerConsumer implements Runnable {

	String kafka = "localhost";
	String topic = "container";

	public void run() {
		System.out.println("Hello from a thread!");

		SimpleConsumer sc = new SimpleConsumer(kafka + ":2181", topic);
		ConsumerConnector consumerConnector = sc.getConsumerConnector();
		System.out.println("Running....." + topic);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		System.out.println(new Integer(1));
		System.out.println(new Integer(1));
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector
				.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while (it.hasNext()) {

			String newString = new String(it.next().message());
			System.out.println("new   " + newString);
			try {
				processMessage(newString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void processMessage(String message) throws Exception {

		Gson gson = new Gson();
		MessageVO mvo = gson.fromJson(message, MessageVO.class);
		System.out.println(mvo.getImagename());
		System.out.println(mvo);
		
		dockerContainerProcess(mvo.getImagename(), mvo.getImagetag());

	}

	public void dockerContainerProcess(String name, String tag) {
		try {

			String target = new String("/home/ujjwal/Documents/container.sh " + name+ " " + tag);
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(target);
			proc.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			System.out.println("### " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/*
 * public static void main(String args[]) { (new Thread(new
 * HelloRunnable())).start(); }
 * 
 * }
 */

