
import java.util.Random;

public class Genetico {
	static int pop[][];
	static int adap[];
	static int adap_nova[];
	static int l = 5;
	static int n = 10;
	static int maior = 0;
	static Random gerador = new Random();

	//static int pop_sel[][]; // guarda os valores da selecao
	static int[]p1;
	static int[]p2;
	static int[]maiorP1;
	static int[]maiorP2;
	
	static int pop_mut[][]; // guarda os cromossomos depois da mutacao
	static int pop_des[][]; // guarda os cromossomos descendentes
	static int pop_inv[][]; // guarda os cromossomos da invers�o
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
	public static void adaptacaoPop() {
		for(int i = 0 ; i<l; i++) {
			for (int j = 0; j<(n-1); j++){
				if (pop[i][j]== 0 && pop[i][j+1] == 1) {
					adap[i] =  adap[i]+1;
				}
			}
		}
	}
	public static int adaptacao(int[] c) {
			int adp=0;		
			for (int j = 0; j<(n-1); j++){
				if (c[j]== 0 && c[j+1] == 1) {
					adp++ ;
				}			
			}
			return adp;
	}
	/*public static void imprime_pop_adap(){
		for(int i = 0 ; i<l; i++) {
			System.out.println("cromossomo " + i);
			for (int j = 0; j<n; j++){
				System.out.println(pop[i][j]);
				System.out.println(" adapta�ao = " + (adap[i]));

			}
		}

	}*/
	public static void seleciona(){
		
		for(int i=0; i<l-1; i++) {
			for(int j = i+1; j<l ; j++) {
				if( adap[i] < adap[j]) {
					for(int k=0; k<n; k++){
						int[] cromossomo = pop[i];
						System.out.println(cromossomo);
						pop[i] = pop[j];
						pop[j] = cromossomo;
					}
					int aux = adap[i];
					adap[i] = adap[j];
					adap[j] = aux;
				}
			}
		}
		
			for(int j = 0; j<n; j++) {
				//System.out.println("p1 "+ pop[0][j]);
				p1[j] = pop[0][j];
				p2[j] = pop[1][j];
				//System.out.println("p1"+p1[j]);
			}
		


	}
	@SuppressWarnings("null")
	public static void cruzamento(){
		int[] f1 = null;
		int[] f2 = null;
		
		for(int i=0; i<2; i++) {
			for(int j = i+1; j<2; j++) {
				int r = gerador.nextInt(10);				
					for(int b=0; b<=r; b++) {
						f1[b] = p1[b];
						f2[b] = p1[b];
					}
					for(int b= r+1; b<n; b++) {
						f1[b] = p1[b];
						f2[b] = p2[b];					
					}
			}
		}
		if((adaptacao(f1)>adaptacao(p1))) {
			for(int i=0; i<n; i++) {
				maiorP1[i] = pop[0][i]=f1[i];
				adap[0] = adaptacao(f1);
				
			}		
		}
		if((adaptacao(f2)>adaptacao(p2))) {
			for(int i=0; i<n; i++) {
				maiorP2[i] =	pop[1][i]=f2[i];
				adap[1] = adaptacao(f2);
			}		
		}

	}
	public static void mutacao(){
		int[] f1 = null;
		f1 = p1;
		int[] f2 = p2;
		for(int j = 0; j<n; j++) {
				int r = gerador.nextInt(10);
				if(r>5) {
					if(p1[j] == 1) {
						f1[j] = 0;
					}
					if (p1[j] == 0){
						f1[j] = 1;
					}					
					if(p2[j] == 1) {
						f2[j] = 0;
					}
					if(p2[j] == 0) {
						f2[j] = 1;
					}				
					
				}
			}
		if((adaptacao(f1)>adaptacao(p1))){			
			if(adaptacao(f1)>adaptacao(maiorP1))
				for(int i=0; i<n; i++) {
					maiorP1[i] = pop[0][i]=f1[i];	
					adap[0] = adaptacao(f1);
			}		
		}
		if((adaptacao(f2)>adaptacao(p2))){			
			if(adaptacao(f1)>adaptacao(maiorP2))
				for(int i=0; i<n; i++) {
					maiorP2[i] = pop[1][i]=f2[i];	
					adap[1] = adaptacao(f2);
			}		
		}
		
	}
	@SuppressWarnings("null")
	public static void inversao(){
			int aux;		
			int r1 = gerador.nextInt(10);
			int r2 = gerador.nextInt(10);
			int[] f1 = null;
			int[] f2 = null;
			
			if(r1>r2) {
				aux = r1;
				r1 = r2;
				r2 = aux;
			}
			
			
			for(int j = 0; j<=r1; j++) {
				f1[j] = p1[j];
				f2[j]= p2[j];
			}
			for(int j = r1+1; j<=r2; j++) {
				f1[j] =p1[j];
				f2[j]= p2[j];
			}
			for(int j = r2+1; j<=n; j++) {
				f1[j] = p1[j];
				f2[j]= p2[j];
			}
			if((adaptacao(f1)>adaptacao(p1))){
				
				if((adaptacao(f1)>adaptacao(p1))) {
					for(int i=0; i<n; i++) {
						maiorP1[i] = pop[0][i]=f1[i];
						adap[0] = adaptacao(f1);
						
					}	
				}
			}
			if((adaptacao(f2)>adaptacao(p2))){
				
				for(int i=0; i<n; i++) {
					maiorP2[i] = pop[1][i]=f2[i];
					adap[1] = adaptacao(f2);
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
	//	Genetico g = new Genetico();
		Genetico.pop = new int[5][10];
		Genetico.adap = new int[10];
		p1 = new int[10];
		p2 =new int[10];
		Genetico.GeraPop ();
		Genetico.adaptacaoPop();
		Genetico.ImprimePop();
		while(adap[0]!=5) {
			Genetico.seleciona();
			//Genetico.cruzamento();
		//Genetico.mutacao();
			//Genetico.inversao();
			//nova_pop();
			//Genetico.calcMaior();
		}
		Genetico.ImprimePop();

	}

}
