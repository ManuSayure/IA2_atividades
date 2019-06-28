package br.ic.ufal;
import java.util.ArrayList;
import java.util.Random;



public class RedeNeural {
		int size = 0;
		static double coeficiente = 0.3;
		static double[][] pesos= {{0, 0, 0},{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		static int linha = 4;
		static int coluna = 3;
		
	  public static double Soma(int entrada[][]) {		  
		double soma = 0;
		for(int i = 0; i<linha; i++) {
			  for(int j = 0; j<coluna; j++) {
				  if(j!=1) {
					  soma = entrada[i][j]*pesos[i][j];
				  }				 
			  }			 
		}
		soma += entrada[1][1]*pesos[1][1];
		return soma;
	  }
	  public static int FuncaoAtivacao2(int entrada[][]) {
		  double soma = (entrada[0][1]*pesos[0][1]) + (entrada[2][1]*pesos[2][1]) + (entrada[3][1]*pesos[3][1]);
		 // System.out.println( ("peso" +pesos[0][1]));
		  
		  if (soma!= 0) return 1;
		  return 0;
	  }
	  
	  public static int FuncaoAtivacao(int entrada[][]) {
		  double soma = Soma(entrada);
		  if (soma>1) return 1;
		  return 0;
	  }
	  public static void Aprendizagem (int entrada[][], int saida) {		  
		  double soma = Soma(entrada);
		  int y = FuncaoAtivacao(entrada);
		 		  
				  while(y!= saida) {
					//  System.out.println(y);
					  for(int i = 0; i<linha; i++) {
						  for(int j = 0; j<coluna; j++) {
							  if(j!=1) {
								  pesos[i][j] = pesos[i][j] + coeficiente*(saida-y)*entrada[i][j]; 
								//  System.out.println("aqui"+i);
								  System.out.println("peso "+ pesos[i][j]);
							  }
						  }
					  }
					  pesos[1][1] = pesos[1][1] + coeficiente*(saida-y)*entrada[1][1];	
					  y = FuncaoAtivacao(entrada);
					 
				  }	
				  System.out.println("aqui 2");
	  }
	  public static void testa (int entrada[][]) {
		  if(FuncaoAtivacao(entrada) == 1 && FuncaoAtivacao2(entrada)== 0) {
			  System.out.println("Achou M");
		  }
		  else System.out.println("Não M");
	  }
	  
	  
	  public static void main(String[] args) {
		  Random gerador = new Random();
		  for(int i = 0; i<linha; i++) {
			  for(int j = 0; j<coluna; j++) {
				   pesos[i][j] =  gerador.nextInt((1 - 0) + 1);
			  }		  
		  }
		  
		  int [][] m = {{1, 0, 1},{1, 1, 1}, {1, 0, 1}, {1, 0, 1}};
		  int [][] n1 = {{1, 0, 1},{1, 1, 1}, {1, 1, 1}, {1, 0, 1}};
		  int [][] n2 = {{0, 0, 0},{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		  int [][] n3 = {{1, 1, 1},{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		  int [][] n4 = {{1, 1, 0},{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
		  
		  Aprendizagem(m, 1);	
		  Aprendizagem(n1, 0);
		  	 
		  Aprendizagem(n2, 0);
		  Aprendizagem(n3, 0);
		  Aprendizagem(n4, 0);
		  Aprendizagem(m, 1);	
		//  System.out.println("peso "+  FuncaoAtivacao2(m));
		 
		  testa(m);
		  testa(n1);
		  testa(n2);
		  testa(n3);
		  testa(n4);  
	  }
}
