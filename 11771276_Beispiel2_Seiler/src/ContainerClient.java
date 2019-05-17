import container.Container;
import container.ContainerElement;
import utils.Logger;

import java.net.*;
import java.util.Scanner;
import java.io.*;
/**
 * Filename: ContainerClient.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 17.05.2019
 */

public class ContainerClient implements Runnable {

	private Container<String> cont;
	private Logger logger;
	private int port;
	private Scanner scanner;
	private boolean runnable = true;
	private String name;
	
	/**
	 * Constructor for class ContainerClient.java
	 * @author Alexander Seiler, 11771276
	 * @param cont
	 */
	public ContainerClient(Container<String> cont, int sendPort, String clientname) {
		this.cont = cont;
		this.port = sendPort;
		this.logger = new Logger(clientname);
		this.scanner = new Scanner(System.in);
		this.name = clientname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		this.logger.info("[start] started thread");
		// TODO Auto-generated method stub
		try {
//			create socket and establish connection
			Socket s = new Socket("localhost", this.port);
//			create new output stream 
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			String postData = "";
			while (this.runnable) {
				System.out.println("[" + this.name + "] Do you want to add an element? Type '0'");
				System.out.println("[" + this.name + "] Do you want to remove an element? Type '1'");
				System.out.println("[" + this.name + "] Do you want to print the container? Type '2'");
				System.out.println("[" + this.name + "] Do you want to exit? Type '3'");
				int userinput = scanner.nextInt();
				scanner.nextLine();
				switch (userinput) {
				case 0:
					// System.out.println("[" + this.name + "] Add: ");
					System.out.println("[" + this.name + "] Please input data to add: ");
					postData = scanner.nextLine();
					this.logger.info("[cmd-add] adding data: " + postData);
					boolean addData = this.cont.add(postData);
					this.logger.debug("[cmd-add] " + (addData == true ? "success" : "fail"));
					pr.println("add");
					pr.flush();
					pr.println(postData);
					pr.flush();
					break;
				case 1:
					// System.out.println("[" + this.name + "] Delete: ");
					System.out.println("[" + this.name + "] Please input data to delete: ");
					postData = scanner.nextLine();
					this.logger.info("[cmd-delete] deleting data: " + postData);
					boolean deleteData = this.cont.remove(new ContainerElement<String>(postData));
					this.logger.debug("[cmd-add] " + (deleteData == true ? "success" : "fail"));
					pr.println("delete");
					pr.flush();
					pr.println(postData);
					pr.flush();
					break;
				case 2:
					this.logger.info("[cmd-print]");
					System.out.println("[" + this.name + "] " + this.cont.toString());
					pr.println("print");
					pr.flush();
					break;
				case 3:
					this.logger.info("[cmd-exit]");
					pr.println("exit");
					pr.flush();
					this.runnable = false;
					break;
				default:
					this.logger.warn("[" + this.name + "] Input not valid!");	
				}
				String answer = bf.readLine();
				this.logger.info("Answer is: " + answer);
			}
			s.close();
			this.logger.info("[close] all sockets closed");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.logger.error(e.getMessage());
			this.logger.error(e.getStackTrace().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.logger.error(e.getMessage());
			this.logger.error(e.getStackTrace().toString());
		}
		this.logger.finalize();
	}
	
}
