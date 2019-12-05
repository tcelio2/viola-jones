package br.com.reconhecedor.imagem.principal;

import java.util.ArrayList;
import java.util.List;

public class MediaMatrizes {

	private static final int DIMENSAO = 3;
	static int[][] matrix1 = new int[DIMENSAO][DIMENSAO];
	static int[][] matrix2 = new int[DIMENSAO][DIMENSAO];
	static int[][] matrix3 = new int[DIMENSAO][DIMENSAO];
	static int[][] matrixFinal = new int[DIMENSAO][DIMENSAO];
	
	static List<int[][]> bunda = new ArrayList<>();
	
	
	public static void main(String[] args) {
		
		//int[] bb = new int[4];
		List<Integer> valueList = new ArrayList<>();
		valueList.add(2);
		valueList.add(7);
		valueList.add(1);
		valueList.add(4);
		int g = 0;
		
		for (int i = 0; i < 4; i++) {
			g += valueList.get(i);
			System.out.println("--"+g);
		}
		
		
		
		matrix1[0][0] = 3;
		matrix1[0][1] = 2;
		matrix1[0][2] = 7;
				
		matrix1[1][0] = 9;
		matrix1[1][1] = 1;
		matrix1[1][2] = 0;
		
		matrix1[2][0] = 2;
		matrix1[2][1] = 2;
		matrix1[2][2] = 5;

		
		matrix2[0][0] = 4;
		matrix2[0][1] = 10;
		matrix2[0][2] = 2;
				
		matrix2[1][0] = 9;
		matrix2[1][1] = 2;
		matrix2[1][2] = 6;
		
		matrix2[2][0] = 8;
		matrix2[2][1] = 1;
		matrix2[2][2] = 3;
		
		
		matrix3[0][0] = 8;
		matrix3[0][1] = 8;
		matrix3[0][2] = 1;
				
		matrix3[1][0] = 9;
		matrix3[1][1] = 5;
		matrix3[1][2] = 0;
		
		matrix3[2][0] = 4;
		matrix3[2][1] = 4;
		matrix3[2][2] = 7;
		
		
		bunda.add(matrix1);
		bunda.add(matrix2);
		bunda.add(matrix3);
		
		for (int m = 0; m < bunda.size(); m++) {
			for (int i = 0; i < DIMENSAO; i++) {
				for (int j = 0; j < DIMENSAO; j++) {
					//int p2 = image.getRGB(i, j);
					//matrix1[i][j]
					//System.out.println("->");
					System.out.println("->"+bunda.get(m)[i][j]);
					matrixFinal[i][j] += bunda.get(m)[i][j];
				
				}
			}			
			System.out.println("---");
		}
		
		
	}

}
