package br.com.reconhecedor.imagem.principal;

public class MatrixArray {

	static int dimensao = 2;
	static int[][] matrix = new int[4][4];
	static int[][] matrix2 = new int[3][4];
	static int[][] matrixResult = new int[3][4];
	
	public static void main(String[] args) {
		
		matrix[0][0] = 3;
		matrix[0][1] = 2;
		matrix[0][2] = 7;
		matrix[0][3] = 15;
		//matrix[0][4] = 7;
		
		matrix[1][0] = 9;
		matrix[1][1] = 1;
		matrix[1][2] = 0;
		matrix[1][3] = 8;
		//matrix[1][4] = 7;
		
		matrix[2][0] = 2;
		matrix[2][1] = 2;
		matrix[2][2] = 5;
		matrix[2][3] = 5;
		//matrix[2][4] = 7;
		
		matrix[3][0] = 3;
		matrix[3][1] = 9;
		matrix[3][2] = 1;
		matrix[3][3] = 11;
		//matrix[3][4] = 7;
		
//		matrix[4][2] = 7;
//		matrix[4][2] = 7;
//		matrix[4][2] = 7;
//		matrix[4][2] = 7;
//		matrix[4][2] = 7;
		
		matrix2[0][0] = 3;
		matrix2[0][1] = 2;
		matrix2[0][2] = 7;
		matrix2[0][3] = 7;
		
		matrix2[1][0] = 9;
		matrix2[1][1] = 1;
		matrix2[1][2] = 0;
		matrix2[1][3] = 1;
		
		matrix2[2][0] = 2;
		matrix2[2][1] = 2;
		matrix2[2][2] = 5;
		matrix2[2][3] = 9;
		
		
		
		//System.out.println(matrix2[0].length);

		//somar(matrix, matrix);
		//incrementarComOutraMatriz(matrix);
	//	multiplicacaoMatrizQuadrada(matrix, matrix);
		//multiplicacaoMatrizesTamanhoDiferentes(matrix, matrix2);
		//resolverMatrix(matrix);
		multiplicarSemResolverMatriz2(matrix);
		//mostrar(matrix);
		
	}
	
	
	private static void mostrar(int[][] matrixResult2) {
		
		for (int i = 0; i <= dimensao; i++) {
			for (int j = 0; j <= dimensao; j++) {
				System.out.println("-->"+matrixResult2[i][j]);
			}
		}
		
	}


	private static void somar(int[][] matrixA, int[][] matrixB) {
	
		for (int i = 0; i <= dimensao; i++) {
			for (int j = 0; j <= dimensao; j++) {
				matrixResult[i][j] = matrixA[i][j] + matrixB[i][j];
			}
		}
	}
	
	private static void incrementarComOutraMatriz(int[][] matrixAdicional) {
		
		for (int i = 0; i <= dimensao; i++) {
			for (int j = 0; j <= dimensao; j++) {
				matrixResult[i][j] = matrixResult[i][j] + matrixAdicional[i][j];
			}
		}
	}
	
	private static void multiplicacaoMatrizQuadrada(int[][] matrixA, int[][] matrixB) {

		for (int i = 0; i <= dimensao; i++) {
			StringBuilder stb = new StringBuilder();
			for (int j = 0; j <= dimensao; j++) {
				for (int k = 0; k <= dimensao; k++) {
					matrixResult[i][j] += (matrixA[i][k] * matrixB[k][j]);
					//stb.append(matrixResult[i][j]);		
				}
				//System.out.println(stb.toString());
				
			}
		}
		System.out.println(matrixResult);
	}

	
	private static void multiplicacaoMatrizesTamanhoDiferentes(int[][] matrixA, int[][] matrixB) {
		int linhas = matrixA.length; //matriz A sempre serao linhas
		int colunas = matrixB[0].length; //matriz B sempre serao colunas
		int[][] resultado = new int[linhas][colunas]; 
		
		for (int i = 0; i < linhas; i++) {
			//StringBuilder stb = new StringBuilder();
			for (int j = 0; j < colunas; j++) {
				for (int k = 0; k < linhas; k++) {
					//int l = matrixA[i][k];
					resultado[i][j] += (matrixA[i][k] * matrixB[k][j]);
					//stb.append(matrixResult[i][j]);		
				}
				//System.out.println(stb.toString());
			}
		}
		System.out.println(resultado);
	}

	
	private static String multiplicarDoisElementos(String a, String b) {
		return null;
	}

//	3  2  7 |  5  4  2  3 
//	9  1  0 |  3  1  8  5
//	2  2  5 |  7  9  3  5
	
  //3*5 + 2*3 + 7*7  3*4 + 2*1 + 7*9   3*2 + 2*8 + 7*3   3*3 + 2*5 + 7*5    
	
	
	
	
	
	
	
	private static void subtrair(int[][] matrixA, int[][] matrixB) { //A - B
		
		for (int i = 0; i <= dimensao; i++) {
			for (int j = 0; j <= dimensao; j++) {
				matrixResult[i][j] = matrixA[i][j] - matrixB[i][j];
			}
		}
	}
	
	private static void dividirTudoPor(int dividendo, int[][] matrix) {
		for (int i = 0; i <= dimensao; i++) {
			for (int j = 0; j <= dimensao; j++) {
				matrixResult[i][j] = matrixResult[i][j] - dividendo;
			}
		}
	}
	
	static int leftSomatoria = 0;
	static int leftValue = 0;
	private static void resolverMatrix(int[][] matrix) {
		int leftResult = 0;
		
		int value = 0;
		//int flag = 0;                        
		for (int i = 0; i <= dimensao; i++) {
			//flag = 0;
			for (int j = 0; j <= dimensao; j++) {
				if(value > dimensao ) {
					value = 0;
				}
				leftResult = matrix[j][j];
				System.out.println("->"+leftResult);
				//flag++;
			}
		    value++;
		}	
	}
//	3  2  7 | 3  2   r ->     0 0 * 1 1 * 2 2   +   0 1 * 1 2 * 2 0   + 0 2 * 1 0 * 2 1  
//	9  1  0 | 9  1
//	2  2  5 | 2  2   l ->     0 2 * 1 1 * 2 0   +   0 0 * 1 2 * 2 1   + 0 1 * 1 0 * 2 2           
//	
//	r = 3*1*5 + 2*0*2 + 7*9*2
//  l = (-7*1*2) + (- 3*0*2) + (2*9*5)  	
	
	
	
	/**
	 * Primeiro esboco!
	 * @param matriz
	 */
	public static void multiplicarSemResolverMatriz(int[][] matriz) {
		
		for (int i = matriz.length; i > 0; i--) {
			int y = 0;
			int b = 0;
			for (int j = 0; j < matriz.length; j++) {
				b += matriz[j][y];
				if(y == 0) {
					y = matriz.length;
				}else {
					y--;
				}
			}
			y++;
		}
		for (int i = matriz.length; i > 0; i--) {
			int k = 0;
			int w = 0;
			for (int j = 0; j < matriz.length; j++) {
				w += matriz[j][k];
				if(k == matriz.length) {
					k = 0;
				}else {
					k++;
				}
			}
			k--;
		}
		
		
	}
	
	/**
	 * Primeiro esboco! versao 2.0
	 * @param matriz
	 */
	public static void multiplicarSemResolverMatriz2(int[][] matriz) {
		int somatoriaLeft = 1;
		int somatoriaRight = 1;

		int finalLeft = 0;
		int finalRight = 0;
		int y = matriz.length - 1;
		int k = 0; 
		for (int i = 0; i < matriz.length; i++) {
			
			for (int j = 0; j < matriz.length; j++) {
				somatoriaLeft  = matriz[j][y] * somatoriaLeft;
				somatoriaRight = matriz[j][k] * somatoriaRight; 
				System.out.println(somatoriaLeft +"<--->"+somatoriaRight);

				if(y == 0) { y = matriz.length - 1; } else { y--; }
				if(k == matriz.length - 1) { k = 0;	} else { k++; }
			}
			if(y == 0) { y = matriz.length - 1; } else { y--; }
			if(k == matriz.length - 1) { k = 0;	} else { k++; }

			finalLeft  += somatoriaLeft;
			finalRight += somatoriaRight;
			somatoriaLeft = 1;
			somatoriaRight = 1;
		}
		
		System.out.println("valor da matriz: ==> "+( finalRight-finalLeft));
	}
	
}
