package br.com.reconhecedor.imagem.violajones.prototipo1;

import java.io.File;
import java.io.IOException;

import br.com.reconhecedor.imagem.principal.Parametros;


public class ViolaJones {

	public static final String PATH = Parametros.PATH;
	public static final int PHOTO_H = Parametros.PHOTO_H;
	public static final int PHOTO_V = Parametros.PHOTO_V;
	
	public static void main(String[] args) throws IOException {
		File foto = new File(Parametros.PATH_PHOTO);
		//FotoUtils fotoUtils = new FotoUtils();
		double[][] fotoVetor = FotoUtils.transformarPhotoEmVetor(foto);
		System.out.println("--");

		FotoUtils.remontarFoto(fotoVetor);
		System.out.println("\n-- Evento finalizado com sucesso!");

	}
	
	
//	private static void montarMatriz() {
//		
//		
//		
//	}
//	
//	private static void transformarPhotoPretoBranco(double[][] foto2) {
//		BufferedImage image = new BufferedImage(PHOTO_H, PHOTO_V, BufferedImage.TYPE_INT_RGB);
//	}
//
//	public static double[][] renormalizePhoto(double[][] foto) {
//		int counter = 0;
//		int TAMANHO = foto.length;
//		double[][] fotoReturn = new double[TAMANHO][TAMANHO];
//		for (int k = 0; k < TAMANHO; k++) {
//			for (int k2 = 0; k2 < TAMANHO; k2++) {
//				
//				fotoReturn[k][k2] = foto[counter][0];
//				counter++;
//			}
//			
//		}
//		return fotoReturn;
//	}
}
