import java.util.Random;

public class principal {
	static int[] arvore = {0, 0, 0, 0, 0};
	static int[] arvore2 = {0, 0, 0, 0, 0};
	static int[] rota =  {0, 0, 0, 0, 0};
	
	static int[][] s1=  {{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
	static int[][] s2=  {{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},{0, 0, 0, 0, 0}};
	static Random gerador = new Random();
	
	/*calcula quantos genes ativos em comum para cada cromossomo da populacao, 
	 gera um matriz, onde cada posicao marca a quantidade de cromossomo em comum na posicao  */
	static void calculaSimilaridade(int m[][], int s[][]) {
		int count=0;
		for(int i=0; i<5; i++) {			 
			for(int k=i+1; k<5; k++) {
				count=0;
				for(int j=0; j<5; j++) {
					
						if(m[i][j]==1 && m[k][j]==1) {
						count++;						
					}					
				}
				s[i][k]= count;
				s[k][i]= count;				
			}			
		}
	}
	/* calcula o valor de similaridade total que cada cromossomo tem em comum com todos os outros, 
	 * ou seja soma todos os valores das linhas da matriz*/
	static void adaptacao(int m1[][], int m2[][]) {
		calculaSimilaridade(m1, s1);
		geraSeq(arvore,s1);
		calculaSimilaridade(m2, s2);
		geraSeq(arvore2,s2);
	}
	/*substitui apenas, se o filho tiver adaptacao maior que o seu pai*/
	public static void substituicao(int m1[][], int m2[][]) {
		adaptacao(m1,m2);
		
		for(int a=0; a<5; a++) {
			if(arvore2[a]>arvore[a]) {
				for(int i=0; i<5; i++) {
					m1[a][i]= m2[a][i];
					s1[a][i] = s2[a][i];
					arvore[a]=arvore2[a];
					
				}
			}
		}
		
	}	
	/*faz o cruzamento com os pais 0 e 1, e gera os filhos 0 e 1*/
	public static void cruzamento(int m1[][], int m2[][]) {
		int r = gerador.nextInt(5);
		for(int a=0; a<2; a++) {
			for(int i=0; i<r; i++) {			 
				m2[a][i]= m1[a][i];	
			}
			
		 for(int i=r+1; i<5; i++ ){
				m2[a][i]= m1[a][i];						
				}	
		 }
		
	}
	/*faz mutacao com os pais 2 e 3 e gera os filhos 2 e 3*/
	public static void mutacao(int m1[][], int m2[][]) {
		
		for(int i=2; i<4; i++) {
			for(int j=0; j<5; j++) {
				if(i!=j) {
					if(m1[i][j] == 0) {
						m2[i][j] = 1;
					}
					else {
						m2[i][j] = 0;
					}
				 }
			 }
		}
	}
	/* faz inversao com o pai 4 e gera o filho 4*/
	public static void inversao(int m1[][], int m2[][]) {
		int r2 = gerador.nextInt(5);
		for(int i=4; i<5; i++) {
			for(int j=0; j<5; j++) {
				m2[i][j]= m1[i][j];
			}
		}
		while(4==r2) {
			 r2 = gerador.nextInt(5);
		}	
		m2[4][r2]= m1[4][r2];
	}
	
	/*o individuo que tiver maior valor na arvore, tera mais cacacteristicas em seu cromossomo,
	 *  logo será o elemento folha da arvorre, porem ness ordenacao o maiores elemento ficam no começo do vetor */
	static void ordena() {
		int x, y;
		for(int i=0; i<=4; i++) {
			for(int j=i; j<=4; j++) {
				if(arvore[i]<arvore[j]) {
					x= arvore[i];
					arvore[i]= arvore[j];
					arvore[j] = x;
					y= rota[i];
					rota[i] = rota[j];
					rota[j] = y;
							
				}
			}
							
		}
		//imprimeArvore();
		
	}
	/*faz o somatório das semelhancas de cada cromossomo com os demais e joga o resutado em arvore*/
	static void geraSeq(int arvore[], int similaridade[][]){		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				arvore[i]+= similaridade[i][j];
				rota[i] = i+1;
			}
		}		
	}
	
		
	static void imprime(int s[][]){
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				System.out.println(s[i][j]);
			}
		}
	}
	//"peso da similaridade: + arvore[i] + "
	
	static void imprimeArvore() {
		for(int i=0; i<5; i++) {
			System.out.println( "  A"+ rota[i]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=5;//quantidade de geracoes
		//imprime();
		
		int[][] pop= {{1, 0, 1, 1, 1},{1, 1, 0, 0, 1}, {1, 1, 1, 1, 0}, {0, 0, 0, 1, 0},{0, 1, 0, 0, 0}};
		
		int[][] pop2= {{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0} ,{0, 0, 0, 0, 0}};
		// o programa tera 5 geracoes
		while(n!=0) {
			cruzamento(pop, pop2);
			mutacao(pop,pop2);
			inversao(pop, pop2);
			substituicao(pop, pop2);			
			n--;		
		}
		ordena();		
		imprimeArvore(); 
		imprime(s1);

	}

}
