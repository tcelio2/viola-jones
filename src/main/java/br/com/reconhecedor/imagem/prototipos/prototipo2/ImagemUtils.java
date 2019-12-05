package br.com.reconhecedor.imagem.prototipos.prototipo2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Jama.Matrix;

public class ImagemUtils {

	public static double[][] calcularMatrizAssinatura(List<File> fotosList) throws IOException {

		File file0 = new File(fotosList.get(0).toString());
		BufferedImage img0 = ImageIO.read(file0); 
		int width0  = img0.getWidth();
		int height0 = img0.getHeight();
		
		double[][] matrizAssinatura = new double[width0*height0][fotosList.size()];
		
		for (int m = 0; m < fotosList.size(); m++) {
			File file = new File(fotosList.get(m).toString());
			BufferedImage img = ImageIO.read(file); 
			int width  = img.getWidth();
			int height = img.getHeight();

			BufferedImage image = ImageIO.read(file);	
			Integer k = 0;
			//int tamanho = fotosList.size();
			try {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {

						int p2 = image.getRGB(i, j);
						matrizAssinatura[k][m] = ImagemUtils.getPixelNormalizado(p2) - Prototipo2.media[k][0];
						k++;
					}
				}
			}catch(Exception e){
				System.out.println("Erro: "+e.getMessage());
			}
		
		}
		
		return matrizAssinatura;
		//Matrix n = new Matrix(matrizAssinatura);
		//System.out.println(n.getRowDimension()+"-+-"+n.getColumnDimension());

		//System.out.println("Matriz de assinatura ==>"+ media[0][0]+"-->"+matrizPrincipal[0][0]);
	}
	
	public static double[][] calcularMedia(double[][] matrix) throws IOException {
		//BufferedImage image = ImageIO.read(file);
		
		int NUMERO_LINHAS = matrix.length;
		int NUMERO_COLUNAS = matrix[0].length;

		double[][] media = new double[NUMERO_LINHAS][1]; //<--- aqui é 1 pois usamos apenas 1 coluna!
		try {
			for (int i = 0; i < NUMERO_LINHAS; i++) {
				for (int j = 0; j < NUMERO_COLUNAS; j++) {

					media[i][0] += matrix[i][j];
					System.out.println(i+"--"+j);
					if(j == NUMERO_LINHAS - 1) {
						media[i][0] = media[i][0] / NUMERO_LINHAS;
					}
				}
			}
		}catch(Exception e){
			System.out.println("Erro: "+e.getCause()+"-"+e.getMessage());
		}
		System.out.println("Matriz de média calculada!");
		return media;
	}

	public static double[][] montarMatrixPadrao(List<File> fotosList) throws IOException {

		File file = new File(fotosList.get(0).toString());
		BufferedImage img = ImageIO.read(file); 
		int width  = img.getWidth();
		int height = img.getHeight();

		int tamanho = fotosList.size();

		double[][] matrizPrincipal = new double[width*height][tamanho];

		for (int m = 0; m < fotosList.size(); m++) {

			BufferedImage image = ImageIO.read(fotosList.get(m));
			int k = 0;
			try {
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {

						int p2 = image.getRGB(i, j);
						System.out.println(p2);
						matrizPrincipal[k][m] = getPixelNormalizado(p2);
						k++;
					}
				}
			}catch(Exception e){
				System.out.println("Erro: "+e.getCause());
			}
			//System.out.println("Matriz de média calculada ==>-->"+Prototipo2.matrizPrincipal[0][0]);
		}
		return matrizPrincipal;


	}


	/**
	 * Trata o pixel e normaliza para um pixel sem cores e padronizado
	 * pixel cinza
	 * 
	 */
	public static Integer getPixelNormalizado(Integer p2) {
		//	System.out.println(p2);
		int a3 = (p2>>24)&0xff;
		int r3 = (p2>>16)&0xff;
		int g3 = (p2>>8)&0xff;
		int b3 =  p2&0xff;
		int avg = (b3+g3+r3)/3;
		int pixel = (a3<<24) | (avg<<16) | (avg<<8) | avg;
		return pixel;
	}
}
