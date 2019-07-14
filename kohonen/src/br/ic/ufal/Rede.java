package br.ic.ufal;

import java.util.Random;


public class Rede {
	static Random gerador = new Random();
	static int linha = 4;
	static int coluna = 3;
	static int rodadas = 2;
	static double ta = 0.8;
	static double[][] pesos= {{0, 0, 0},{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	static int[][] entrada = {{1,3,5},{1, 4, 2}, {3,4,1}, {5,2,1}};
	static int[] saida = {0, 0, 0, 0};
	
	
	
	/*
	Entrada: um vetor em que as entradas são os valores utilizados no treinamento da rede
	neural, número máximo de iterações iter_max e taxa de aprendizado η.
	Saída: rede neural de Kohonen treinada.
	1 iter ← 1;
	2 atribua pesos aleatórios aos neurônios;
	3 enquanto ( iter ≤ iter_max ) faça
	4 selecione um valor aleatório do conjunto de treinamento;
	5 encontre o neurônio vencedor que apresentar maior correlação ao valor selecionado;
	6 encontre os neurônios vizinhos do neurônio vencedor;
	7 os pesos sinápticos dos neurônios são modificados, considerando-se a taxa de
	aprendizado η;
	8 reduza o valor de η;
	9 iter ← iter + 1;
	10 fim-enquanto;
	11 retorna rede neural de Kohonen treinada.*/
	public static void atualizaPeosVizinhos(int n, int m, int min) {
		//w(i, j) = w(i, j) + η(xj − w(i, j))
		if(n==0) {
			pesos[3][m] +=  ta*(min - pesos[3][m]);
			pesos[n][m] +=  ta*(min - pesos[n][m]);
			pesos[n+1][m] +=  ta*(min - pesos[n+1][m]);
		}
		else if(n==3) {
			pesos[n-1][m] +=  ta*(min - pesos[n-1][m]);
			pesos[n][m] +=  ta*(min - pesos[n][m]);
			pesos[0][m] +=  ta*(min - pesos[0][m]);
		}
		else {
			pesos[n-1][m] +=  ta*(min - pesos[n-1][m]);
			pesos[n][m] +=  ta*(min - pesos[n][m]);
			pesos[n+1][m] +=  ta*(min - pesos[n+1][m]);
		}
		
		
	}
	
	
	public static void treinamento() {
		int min = 0;
		int n = 0, m = 0;
		while(rodadas>0) {
			int k=0;			
			while(k <4) {				
				 n = k;
				 for(int i = 0 ; i<2; i++) {
					 
					min = entrada[n][i];
					if(entrada[n][i+1]<min) {
						min = entrada[n][i+1];
						m=i+1;					
					}
				 }
				saida[k]= min;
				k++;	
				atualizaPeosVizinhos(n, m, min);
			}			
			rodadas--;
		}
		
		
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Random gerador = new Random();
		  int count = 0;
		  for(int i = 0; i<linha; i++) {
			 
			for(int j = 0; j<coluna; j++) {
				   pesos[i][j] =  gerador.nextInt((1 - 0) + 1);
			  }		  
		  }
		  
		  treinamento();
		  System.out.println("Menor rota -> " + saida[0] + "->" + saida[1]+ "->" + saida[2] + "->" + saida[3]);
		  for(int i = 0; i<linha; i++) {
				 
				for(int j = 0; j<coluna; j++) {
					 System.out.println(  pesos[i][j] );
				  }		  
			  }
	}

}
