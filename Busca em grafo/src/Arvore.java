import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Arvore {
	No vetor[] = new No[7];	
	
	
	public static void main(String[] args) {
		
		int entrada[][] = {{0,0},{0,1},{1,0},{1,1}};
		int saida[] = {-1, 2, 1, 4} ;		
		Arvore a = new Arvore();
		No novo ;
	
			a.vetor[3] = new No(1 ,1 ,0); 
			int k=0;
			
		for(int i=2; i>=0 ; i--) {
			a.vetor[i]= new No(2+k ,1+k ,-1); k++;
					
		}
		 k=0;
		for(int i=4; i<7 ; i++) {
			a.vetor[i]= new No(1+k ,2+k ,-1); k++;
				
		
		}	
		int index = calcula( a, entrada, saida);
		
				
		
	System.out.println("Equação gerada será " + a.vetor[index].getA() +"x + "+ a.vetor[index].getB()+"y " + a.vetor[index].getC());
	System.out.println("Equação gerada será " + a.vetor[index].getA() +"*0 + "+ a.vetor[index].getB()+"*0 " + a.vetor[index].getC() + "= -1");
	System.out.println("Equação gerada será " + a.vetor[index].getA() +"*0 + "+ a.vetor[index].getB()+"*1 " + a.vetor[index].getC() + "= -2");
	System.out.println("Equação gerada será " + a.vetor[index].getA() +"*1 + "+ a.vetor[index].getB()+"0 " + a.vetor[index].getC() + "= 1");
	System.out.println("Equação gerada será " + a.vetor[index].getA() +"*1 + "+ a.vetor[index].getB()+"*1 " + a.vetor[index].getC() + "= 4");
}


	private static int calcula(Arvore a, int entrada[][], int saida[]) {			
			int f = -1;
			boolean s = false;
			for(int i=0; i<7; i++) {
					int count = 1;	
					System.out.println(f);
					for(int j=0; j<4; j++) 
					{
						int resultado = (a.vetor[i].a) *(entrada[j][0]) + (a.vetor[i].b) *(entrada[j][1]) + (a.vetor[i].c) ;
						if(resultado == saida[j]) {
							System.out.println(resultado);
							count ++;									
						}
						if(count == 4) {
							return i;
						}
					}
					f=i;
						
			}
			
			return f;
	}
}
