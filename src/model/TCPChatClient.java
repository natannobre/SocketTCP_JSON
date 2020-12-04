package model;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPChatClient {

	public static void main(String[] args) {
		
		try {
                    
                    Socket s = new Socket( "127.0.0.1", 8000 );

                    OutputStream os = s.getOutputStream();
                    DataOutputStream dataOS = new DataOutputStream( os );

                    InputStream is = s.getInputStream();
                    DataInputStream dataIS = new DataInputStream( is );

                    boolean running = true;

                    while( running ) {

                        //Digitando mensagem para o servidor
                        System.out.print("Digite a mensagem: ");
                        Scanner scanner = new Scanner(System. in);
                        String inputString = scanner. nextLine();

                        //Criando o objeto mensagem
                        Mensagem mensagem = new Mensagem( inputString );

                        //Criando objeto Json
                        Gson gson = new Gson();
                        String contatoString = gson.toJson( mensagem );

                        //Mandando a mensagem pela rede
                        dataOS.writeUTF( contatoString );			

                        //Esperando resposta do servidor
                        String message = dataIS.readUTF();

                        //Convertendo: De string message(modelo Json) para objeto Mensagem
                        Mensagem resposta = gson.fromJson( message, Mensagem.class );

                        //Imprimindo mensagem recebida
                        System.out.println( "Mensagem tratada pelo servidor:\n" + resposta.getConteudo() );

                        System.out.println( "\n\nDeseja parar? Digite Sim" );		        
                        inputString = scanner. nextLine();

                        if( inputString.startsWith( "S") ) running = false;

                    }

                    dataOS.close();
                    dataIS.close();						
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
