package br.com.reconhecedor.imagem.principal;

import java.util.HashMap;
import java.util.Map;

public class MatrizesReais {

	static Integer[][] matrix1  = new Integer[3][3];
	static Integer[][] matrix2 = new Integer[3][3];
	static Integer[][] x = new Integer[3][3];
	
	static Integer[][] potencia1 = new Integer[3][3];
	static Integer[][] potencia2 = new Integer[3][3];
	
	public static void main(String[] args) {
	
		
		
		matrix1[0][0] = 3;
		matrix1[0][1] = 2;
		matrix1[0][2] = 7;
				
		matrix1[1][0] = 9;
		matrix1[1][1] = 1;
		matrix1[1][2] = 0;
		
		matrix1[2][0] = 2;
		matrix1[2][1] = 2;
		matrix1[2][2] = 5;

		matrix2[0][0] = 3;
		matrix2[0][1] = 2;
		matrix2[0][2] = 7;
				
		matrix2[1][0] = 9;
		matrix2[1][1] = 1;
		matrix2[1][2] = 0;
		
		matrix2[2][0] = 2;
		matrix2[2][1] = 2;
		matrix2[2][2] = 5;
		
//		matrix2[0][0] = 1;
//		matrix2[0][1] = 0;
//		matrix2[0][2] = 0;
//		
//		matrix2[1][0] = 0;
//		matrix2[1][1] = 1;
//		matrix2[1][2] = 0;
//		
//		matrix2[2][0] = 0;
//		matrix2[2][1] = 0;
//		matrix2[2][2] = 1;
		
		
		x[0][0] = 1;
		x[0][1] = 0;
		x[0][2] = 0;
		
		x[1][0] = 0;     //1 0 0
		x[1][1] = 1;     //0 1 0
		x[1][2] = 0;     //0 0 1
		
		x[2][0] = 0;
		x[2][1] = 0;
		x[2][2] = 1;
		
		
		
		//multiplicacaoMatrizesTamanhoDiferentes(matrix1, x);
	}
	
	private static void multiplicacaoMatrizPor(Integer[][] matrixA, Integer[][] var) {
		int linhas = matrixA.length; //matriz A sempre serao linhas
		int colunas = var[0].length; //matriz B sempre serao colunas
		
		Integer[][] resultado = new Integer[linhas][colunas];
		Integer[][] variavel = new Integer[linhas][colunas];
		
		for (int i = 0; i < linhas; i++) {

			for (int j = 0; j < colunas; j++) {
			
				for (int k = 0; k < linhas; k++) {
					
					if(var[i][j] != 0) {
						resultado[i][j] = multiplicarMatrizNumero(matrixA[i][k],  var[k][j]);
						//variavel[i][j]  += multiplicarMatrizVariavel(matrixA[i][k],  var[k][j]);
					} else {
						
					}
					
					 
					
				}
			}
		}
		System.out.println(resultado);
	}
	//3 *(1 -y) + 2 * 0       + 7 * 0
	//9 * 0     + 1 *(1 - y)  + 0 * 0
	//2 * 0     + 2 * 0       + 5 * (1 - y)
	
	//3 2 7
	//9 1 0
	//2 2 5
	public static Integer multiplicarMatrizNumero(Integer one, Integer two) {
		Integer retorno = 0;
		
		
		
		return null;
	}
	
	
	
}
