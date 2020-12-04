package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import thread.ThreadsJava;

public class TCPChatServer {
	
	private static boolean running;
	private static ArrayList<ThreadsJava> listaThreads;
	
	public static void main(String[] args) {
		
		try {
			
			running = true;
			
			listaThreads = new ArrayList<ThreadsJava>();
			
			ServerSocket server = new ServerSocket( 8000 );	
			System.out.println( "Servidor Iniciado" );
			
			while( running ) {
				
				Socket s = server.accept();
				
				ThreadsJava thread = new ThreadsJava( s );
				thread.start();
				listaThreads.add( thread );
				

			}
			
			System.out.println( "Servidor Encerrado" );
			
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
        
}