package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import model.Mensagem;

public class ThreadsJava extends Thread {

	private boolean running;
	private static int contador = 0;
	private int idThread;
	
	private Socket socket;
	
	public ThreadsJava( Socket socket ) {
		this.running = false;
		this.idThread = contador++;
		this.socket = socket;
	}
	
	
	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public void run() {
		
		this.running = true;
		
		while( running ) {
			
			
			try {
				
                            InputStream is = socket.getInputStream();
                            DataInputStream dataIS = new DataInputStream( is );

                            OutputStream os = socket.getOutputStream();
                            DataOutputStream dataOS = new DataOutputStream( os );

                            //System.out.println( "Id: " + idThread + " - Aceitando conexão do cliente " + socket.getInetAddress() );


                            // create a DataInputStream so we can read data from it.
                            // read the message from the socket
                            String message = dataIS.readUTF();

                            Gson g = new Gson();
                            Mensagem mensagem = g.fromJson( message, Mensagem.class );

                            String conteudoMensagem = mensagem.getConteudo();
                            System.out.println( "Cliente " + idThread + " disse: " + conteudoMensagem );

                            conteudoMensagem = ("De: Cliente " + idThread + " : " + conteudoMensagem);

                            Mensagem m = new Mensagem( conteudoMensagem );
                            String jsonMensagem = g.toJson( m );

                            //Respondendo de volta para o cliente pela rede
                            dataOS.writeUTF( jsonMensagem );
				
			} catch (JsonSyntaxException | IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            this.running = false;
			}
			
		}
		
	}
	
	
}