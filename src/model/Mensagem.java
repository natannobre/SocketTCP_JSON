package model;

public class Mensagem {
	
	private static int contador = 0;
	private int id;
	private String conteudo;
	
	public Mensagem(String conteudo) {
		super();
		this.conteudo = conteudo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
}
