package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import PaymentSystem.PaymentSystemView;
import model.Payment_History;
import model.managed.Managed_PaymentSystem;

public class ServerThread extends Thread implements Runnable{
    Socket socket;
    Payment_History paymentHistory = new Payment_History();
    PaymentSystemView psview;

    public ServerThread(Socket sock, PaymentSystemView psv){
        this.socket = sock;
        this.psview = psv;
    }
    
    public static void startServer() {
    	try {
			PaymentSystemView psv = new PaymentSystemView();
			psv.setVisible(true);
			
			@SuppressWarnings("resource")
			ServerSocket serversocket = new ServerSocket(1314);
			System.out.println("Server start " + InetAddress.getLocalHost().getHostAddress());
			
			while(true){
				Socket sock = serversocket.accept();
				ServerThread serverThread = new ServerThread(sock, psv);
				
				serverThread.start();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Sorry, cannot start the server!","Error",1);
        }
    }
    
    @Override
    public void run(){
    	String admin = "Admin_Payment";

    	try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            String id = reader.readLine().trim();
            paymentHistory.setMaGD(reader.readLine().trim());
            paymentHistory.setCMND(reader.readLine().trim());
            paymentHistory.setDebit(Double.parseDouble(reader.readLine().trim()));
            paymentHistory.setMinDebt(Double.parseDouble(reader.readLine().trim()));
            paymentHistory.setTime(Timestamp.valueOf(reader.readLine().trim()));
            
            Double balance_Admin = Double.parseDouble(Managed_PaymentSystem.getBalance(admin)) + paymentHistory.getMinDebt();
            Double balance_User = Double.parseDouble(Managed_PaymentSystem.getBalance(id)) - paymentHistory.getMinDebt();
            Double debt_User = Double.parseDouble(Managed_PaymentSystem.getDebt(id)) - paymentHistory.getDebit();
            
            if(Managed_PaymentSystem.updateBalance(balance_Admin, admin) && Managed_PaymentSystem.updateBalance(balance_User, id)
            && Managed_PaymentSystem.updateDebt(debt_User,id) && Managed_PaymentSystem.insertPayment(paymentHistory) ){
            	
            	psview.setBalanceCurrentText(Managed_PaymentSystem.getBalance(admin));
            	writer.write("Successful!");
                writer.write("\r\n");
                writer.flush();
            }
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }
}