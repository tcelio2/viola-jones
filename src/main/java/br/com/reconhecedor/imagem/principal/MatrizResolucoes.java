package br.com.reconhecedor.imagem.principal;

public class MatrizResolucoes {

	static int dimensao = 3;
	static int left = 0;
	static int somatoria = 0;
	static int[][] matrizIdentidade = new int[dimensao][dimensao];
	
	
	public static void main(String[] args) {

		
		//BigDecimal num = new BigDecimal("12.0");
		
		//System.out.println("->"+num.toString().replace(".0", ""));
		//System.out.println("->"+num);
		int[][] matrix = new int[dimensao][dimensao];
		
		matrix[0][0] = 3;
		matrix[0][1] = 4;
		matrix[0][2] = 2;
		
		matrix[1][0] = 5;
		matrix[1][1] = 1;
		matrix[1][2] = 0;
		
		matrix[2][0] = 1;
		matrix[2][1] = 4;
		matrix[2][2] = 8;
		// 00 01 02
		// 10 11 12
		// 20 21 22
		
//		resolverMatrizLeft(matrix);
//		resolverMatrizRight(matrix);
//		System.out.println("Result:"+somatoria);
		//obterMatrizIdentidade();
		transformarTransposta(matrix);
	}
	
	public static void multiplicarIdentidadeComMatriz(int[][] matriz) {
		
		for (int i = 0; i < matriz.length; i++) {
			
			for (int j = 0; j < matrizIdentidade.length; j++) {
				
			}
			
		}

	}
	
	public static void transformarTransposta(int[][] matrix) {
		int[][] matrixTemp = new int[dimensao][dimensao];
		for (int i = 0; i < dimensao; i++) {
			System.out.println("\t");
			for (int j = 0; j < dimensao; j++) {
				matrixTemp[i][j] = matrix[j][i];
				System.out.println(i +" "+ j);
			}
		}								
		
		mostrarMatriz(matrixTemp);
		//System.out.println(matrix);
		//System.out.println("\n\n");
		//System.out.println(matrixTemp);
	}
	
	public static void obterMatrizIdentidade() {
		
		int marcador = 0;
		for (int i = 0; i < dimensao; i++) {
			
			for (int j = 0; j < dimensao; j++) {
				matrizIdentidade[i][j] = (j == marcador)? 1 : 0;
			}
			marcador++;
		}
		
		mostrarMatriz(matrizIdentidade);
		
	}
	/*
	1 0 0 0 0 
	0 1 0 0 0
	0 0 1 0 0
	0 0 0 1 0
	0 0 0 0 1
	*/
	public static void mostrarMatriz(int[][] matrix) {
		
		for (int i = 0; i < matrix.length; i++) {
			StringBuilder stb = new StringBuilder();
			for (int j = 0; j < matrix.length; j++) {
				stb.append(matrix[i][j]);		
			}
			System.out.println(stb.toString());
			
		}
	}
	
}
