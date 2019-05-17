import container.Container;
import container.ContainerElement;
import utils.Logger;

import java.net.*;
import java.io.*;
/**
 * Filename: ContainerServer.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 17.05.2019
 */

public class ContainerServer implements Runnable {

	private Container<String> cont;
	private Logger logger;
	private int receivePort;
	private boolean runnable = true;
	private Socket sendSocket = null;
	private PrintWriter sendWriter = null;
	private String name;
	
	/**
	 * Constructor for class ContainerServer.java
	 * @author Alexander Seiler, 11771276
	 * @param cont
	 */
	public ContainerServer(Container<String> cont, int receivePort, String servername) {
		this.cont = cont;
		this.receivePort = receivePort;
		this.logger = new Logger(servername);
		this.name = servername;
	}

	/**
	 * Constructor for class ContainerServer.java
	 * @author Alexander Seiler, 11771276
	 * @param cont
	 * @param receivePort
	 * @param sendPort
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ContainerServer(Container<String> cont, int receivePort, int sendPort, String servername) throws UnknownHostException, IOException {
		this.cont = cont;
		this.receivePort = receivePort;
		this.sendSocket = new Socket("localhost", sendPort);
		this.sendWriter = new PrintWriter(this.sendSocket.getOutputStream());
		this.logger = new Logger(servername);
		this.name = servername;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.logger.info("[start] started thread");
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(this.receivePort);
			Socket s = ss.accept();
			this.logger.info("Successfully connected to " + s.getInetAddress().toString() + ":" + this.receivePort);
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			
			while (this.runnable) {
				String command = bf.readLine();
				String data;
				if (command.equalsIgnoreCase("add")) {
					data = bf.readLine();
					this.logger.info("[cmd-add] Adding data: " + data);
					boolean addData = this.cont.add(data);
					this.logger.debug("[cmd-add] " + (addData == true ? "success" : "fail"));
					String retData = "";
					if (this.sendSocket != null) {
						retData += this.executeCommand("add", data);
					}
					String answer = "[" + this.name + "-add] " + (addData == true ? "success" : "fail") + (retData.equals("") ? "" : " |  " + retData);
					pr.println(answer);
					pr.flush();
				} else if (command.equals("delete")) {
					data = bf.readLine();
					this.logger.info("[cmd-delete] Deleting data: " + data);
					boolean deleteData = this.cont.remove(new ContainerElement<String>(data));
					this.logger.debug("[cmd-delete] " + (deleteData == true ? "success" : "fail"));
					String retData = "";
					if (this.sendSocket != null) {
						retData += this.executeCommand("delete", data);
					}
					String answer = "[" + this.name + "-delete] " + (deleteData == true ? "success" : "fail") + (retData.equals("") ? "" : " |  " + retData);
					pr.println(answer);
					pr.flush();
				} else if (command.equals("print")) {
					System.out.println("[" + this.name + "] " + this.cont.toString());
					String retData = "";
					if (this.sendSocket != null) {
						retData += this.executeCommand("print", "");
					}
					pr.println("[" + this.name + "-printed]" + (retData.equals("") ? "" : " | " + retData));
					pr.flush();
				} else if (command.equals("exit")) {
					this.logger.info("exiting");
					this.runnable = false;
					String retData = "";
					if (this.sendSocket != null) {
						retData += this.executeCommand("exit", "");
					}
					pr.println("[" + this.name + "-exited]" + (retData.equals("") ? "" : " | " +retData));
					pr.flush();
				} else {
					this.logger.info("Unknown command");
					pr.println("[" + this.name + "] Unknown command");
					pr.flush();
				}
			}
			s.close();
			ss.close();
			if (this.sendSocket != null) this.sendSocket.close();
			this.logger.info("[close] all sockets closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.logger.error(e.getMessage());
			this.logger.error(e.getStackTrace().toString());
		}
	}
	
	private String executeCommand(String command, String data) throws IOException {
		if (this.sendSocket == null) return "No send-socket defined";
		if (this.sendWriter == null) return "No send-writer defined";
		this.sendWriter.println(command);
		this.sendWriter.flush();
		if (!data.equals("")) {
			this.sendWriter.println(data);
			this.sendWriter.flush();
		}
		InputStreamReader in = new InputStreamReader(this.sendSocket.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		String retval = bf.readLine();
		this.logger.trace("Receiving value: " + retval);
		return retval;
	}

}
