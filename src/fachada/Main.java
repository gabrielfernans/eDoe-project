package fachada;

import java.util.Scanner;

import easyaccept.EasyAccept;

public class Main {
	
	public static void main(String[] args) {
		args = new String[] {"fachada.Fachada", "accept_testes/use_case_1.txt", 
				"accept_testes/use_case_2.txt", "accept_testes/use_case_3.txt", "accept_testes/use_case_4.txt"};
		EasyAccept.main(args);

	}
}
