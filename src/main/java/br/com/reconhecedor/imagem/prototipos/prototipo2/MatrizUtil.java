package br.com.reconhecedor.imagem.prototipos.prototipo2;

import java.text.DecimalFormat;

public class MatrizUtil {


	public static void mostrarMatriz(double[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			//StringBuilder stb = new StringBuilder();
			for (int j = 0; j < matrix.length; j++) {

				DecimalFormat formatter = new DecimalFormat("##.####");


				//double value = Double.parseDouble(new DecimalFormat("############.############").format(matrix[i][j]));

				//				NumberFormat formatter = new DecimalFormat("#.####");
				//				String string = formatter.format(matrix[i][j]);
				System.out.println(formatter.format(matrix[i][j]));
				//System.out.println(value);

				//stb.append(matrix[i][j]);		
			}	
			//System.out.println(stb.toString());

		}
	}

	public static void mostrarMatriz(float[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			StringBuilder stb = new StringBuilder();
			for (int j = 0; j < matrix.length; j++) {
				stb.append(matrix[i][j]);		
			}
			System.out.println(stb.toString());

		}
	}
}
