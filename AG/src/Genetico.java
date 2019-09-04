
import java.util.Random;

public class Genetico {
	static int pop[][];
	static int adap[];
	static int adap_nova[];
	static int l = 5;
	static int n = 10;
	static int maior = 0;
	static Random gerador = new Random();
	
	static int pop_sel[][]; // guarda os valores da selecao
	static int pop_mut[][]; // guarda os cromossomos depois da mutacao
	static int pop_des[][]; // guarda os cromossomos descendentes
	static int pop_inv[][]; // guarda os cromossomos da inversão
	static int pop_nova[][];
	 
	public static void GeraPop () {
		for(int i = 0 ; i<l; i++) {
			for (int j = 0; j<n; j++){
				pop[i][j] = gerador.nextInt((1 - 0) + 1);
				adap[i] = 0;
			}
		}
		
	}
	public static void ImprimePop() {
		for(int i = 0 ; i<l; i++) {
			System.out.println("cromossomo " + i);
			for (int j = 0; j<n; j++){
				System.out.println(pop[i][j]);							
			}
		}		
	}
	public static void Adaptacao() {
		for(int i = 0 ; i<l; i++) {
			for (int j = 0; j<(n-1); j++){
				if (pop[i][j]== 0 && pop[i][j+1] == 1) {
					adap[i] =  adap[i]+1;
				}
			}
		}	
	}
	public static void imprime_pop_adap(){
		for(int i = 0 ; i<l; i++) {
			System.out.println("cromossomo " + i);
			for (int j = 0; j<n; j++){
				System.out.println(pop[i][j]);	
				System.out.println(" adaptaçao = " + (adap[i]));
				
			}
		}	
		
	}
	public static void seleciona(){
		for(int i=0; i<l-1; i++) {
			for(int j = i+1; j<l ; j++) {
				if( adap[i] < adap[j]) {
					int[] cromossomo = pop[i];
					pop[i] = pop[j];
					pop[j] = cromossomo;
					int aux = adap[i];
					adap[i] = adap[j];
					adap[j] = aux;
				}					
			}			
		}
		for(int i=0; i<2; i++) {
			for(int j = 0; j<n; j++) {
				pop_sel[i][j] = pop[i][j];
			}
		}
		
		
	}
	public static void cruzamento(){
		
		for(int i=0; i<2; i++) {
			for(int j = i+1; j<2; j++) {
				int r = gerador.nextInt(10);
				for(int a = 0; a<l; a++ ) {
					for(int b=0; b<=r; b++) {
						pop_des[a][b]=pop_sel[i][b];
								pop_des[a+1][b]=pop_sel[j][b];
					}
					for(int b= r+1; b<n; b++) {
						pop_des[a][b]=pop_sel[j][b];
						pop_des[a+1][b]=pop_sel[i][b];
						
					}
				}
			}
		}
		
	}
	public static void mutacao(){
		for(int i=0; i<2; i++) {
			for(int j = 0; j<n; j++) {
				int r = gerador.nextInt(10);
				if(r>5) {
					if(pop_sel[i][j] == 1) {
						pop_mut[i][j] = 0;
					}
					else {
						pop_mut[i][j] = pop_sel[i][j];
					}
				}
			}
			}
		
	}
	public static void inversao(){
		int aux;
		for(int i=0; i<2; i++) {
			int r1 = gerador.nextInt(10);
			int r2 = gerador.nextInt(10);
			if(r1>r2) {
				aux = r1;
				r1 = r2;
				r2 = aux;
			}
			for(int j = 0; j<=r1; j++) {
				pop_inv[i][j] = pop_sel[i][j];
			}
			for(int j = r1+1; j<=r2; j++) {
				pop_inv[i][j] = pop_sel[i][j];
			}
			for(int j = r2+1; j<=n; j++) {
				pop_inv[i][j] = pop_sel[i][j];
			}
			
		}		
		
	}
	public static void nova_pop() {
		for(int i=0; i<2; i++) {
			for(int j = 0; j<n; j++) {
				pop_nova[i][j] = pop_sel[i][j];
			}
		}
		for(int i=2; i<4; i++) {
			for(int j = 0; j<n; j++) {
				pop_nova[i][j] =  pop_des[i][j];
			}
		}
		for(int i=4; i<6; i++) {
			for(int j = 0; j<n; j++) {
				pop_nova[i][j] =  pop_mut[i][j];
			}
		}
		for(int i=6; i<8; i++) {
			for(int j = 0; j<n; j++) {
				pop_nova[i][j] =  pop_inv[i][j];
			}
		}
		for(int i=0; i<8-1; i++) {
			for(int j = 0; j<8; j++) {
				if ((pop_nova[i][j] == 0) && (pop_nova[i][j+1] == 1)) {
					adap_nova[i] = adap_nova[i + 1];
				}
			}
		}
		for(int i=0; i<8-1; i++) {
			for(int j = i+1; j<8; j++) {
				if(adap_nova[i] < adap_nova[j]) {
					int[] cromossomo = pop_nova[i];
							pop_nova[i] = pop_nova[j];
							pop_nova[j] = cromossomo;
							int aux = adap_nova[i];
							adap_nova[i] = adap_nova[j];
							adap_nova[j] = aux;
				}
				
			}
		}
		for(int i=0; i<l; i++) {
			for(int j = 0; j<n; j++) {
				pop[i][j] = pop_nova[i][j];
			}
		}
		
		
			
		
		
		
	}
	
	public static void calcMaior() {
		for(int i=0; i<=5; i++) {
			if(maior<i) {
				maior=i;
			}
		}
		
	}
	

	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(maior!=5) {
			seleciona();
			cruzamento();
			mutacao();
			inversao();
			nova_pop();
			calcMaior();
		}
		imprime_pop();

	}

}
