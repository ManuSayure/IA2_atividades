import java.util.ArrayList;
import java.util.List;

public class No {
	 

	    int a, b, c; //dado  
	    No filho; //cria filho  a esquerda 

	    public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}

		public No getFilho() {
			return filho;
		}

		public void setFilho(No filho) {
			this.filho = filho;
		}

		public No(int a, int b, int c)
	    {  
	        this.a = a;
	        this.b = b;
	        this.c = c;
	    }  

	     public int calculaNo(int x, int y)   
	       {    
	    	  int resultado = (a*x) + (b*y) + c ;

	             return (resultado);    
	               

	        }       

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
