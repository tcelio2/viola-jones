package br.com.reconhecedor.imagem.violajones.prototipo1;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.com.reconhecedor.imagem.principal.Parametros;

public class FotoUtils {

	public static final String PATH_PHOTO_RESULT = Parametros.PATH_PHOTO_RESULT;

	public static void remontarFoto(double[][] foto) {

			int TAMANHO = Parametros.PHOTO_H;
		BufferedImage image = new BufferedImage(Parametros.PHOTO_H, Parametros.PHOTO_V, BufferedImage.TYPE_INT_RGB);
			
		for (int k = 0; k < TAMANHO; k++) {
			for (int k2 = 0; k2 < TAMANHO; k2++) {
				int a2 = ((int)foto[k][k2]>>24) & 0xff; //alpha
				int r2 = ((int)foto[k][k2]>>16) & 0xff; //red
				int g2 = ((int)foto[k][k2]>>8) & 0xff; //green
				int b2 = ((int)foto[k][k2]) & 0x0ff; //blue
				int bbb =  (a2<<24) | (r2<<16) | (g2<<8) | b2; //red
				
				image.setRGB(k, k2, bbb);
			}
			
		}
		File f2 = null;
		f2 = new File(PATH_PHOTO_RESULT);
		try {
			ImageIO.write(image, "jpg", f2);
		} catch (IOException e) {
			System.out.println("Erro ao criar:" + e.getCause());		
		}
	}
	
	public static double[][] renormalizePhoto(double[][] foto) {
		int counter = 0;
		int TAMANHO = foto.length;
		double[][] fotoReturn = new double[TAMANHO][TAMANHO];
		for (int k = 0; k < TAMANHO; k++) {
			for (int k2 = 0; k2 < TAMANHO; k2++) {
				
				fotoReturn[k][k2] = foto[counter][0];
				counter++;
			}
			
		}
		return fotoReturn;
	}
	
	public static double[][] transformarPhotoEmVetor(File file) throws IOException {
		
		int width = Parametros.PHOTO_H;
		int height = Parametros.PHOTO_V;
		
		double[][] matrizPrincipal = new double[width][height];

		

			BufferedImage image = ImageIO.read(file);

			try {
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {

						int color = image.getRGB(i, j);

						System.out.println("-->"+color+"  azul:"+(color & 0xff)+" green:"+((color & 0xff00) >> 8)+" red:"+((color & 0xff0000) >> 16));

						matrizPrincipal[i][j] = tranformarPretoBranco(color);

					}
				}
			}catch(Exception e){
				System.out.println("Erro: "+e.getCause());
			}
			return matrizPrincipal;
	}
	
	/**
	 * Trata o pixel e normaliza para um pixel sem cores e padronizado
	 * pixel cinza
	 * 
	 */
	public static Integer tranformarPretoBranco(Integer p2) {

		int a3 = (p2>>24)&0xff;
		int r3 = (p2>>16)&0xff;
		int g3 = (p2>>8)&0xff;
		int b3 =  p2&0xff;
		int avg = (b3+g3+r3)/3;
		int pixel = (a3<<24) | (avg<<16) | (avg<<8) | avg;
		return pixel;
	}
	/**
	 *  1111111111
	 *  1111111111
	 *  0000000000
	 *  0000000000
	 *  
	 */
	public static double obterPorFeature1(double[][] janela, double treshold) {
		double num = 0;
		return num;
//		int counter = 0;
//		int TAMANHO = 0;
//		double[][] fotoReturn = new double[TAMANHO][TAMANHO];
//		for (int k = 0; k < TAMANHO; k++) {
//			for (int k2 = 0; k2 < TAMANHO; k2++) {
////				
////				fotoReturn[k][k2] = foto[counter][0];
////				counter++;
//			}
//			
//		}
//		return fotoReturn;
	}
	
	/**
	 *  1111100000
	 *  1111100000
	 *  0000011111
	 *  0000011111
	 */
	public static double obterPorFeature2() {
		double num = 0;
		return num;
	}
	
	/**
	 *  1111100000
	 *  1111100000
	 *  1111100000
	 *  1111100000
	 *  
	 */
	public static double obterPorFeature3() {
		double num = 0;
		return num;
	}
	

	/**
	 *  1110000111
	 *  1110000111
	 *  1110000111
	 *  1110000111
	 *  
	 */
	public static double obterPorFeature4() {
		double num = 0;
		return num;
	}
	
}
