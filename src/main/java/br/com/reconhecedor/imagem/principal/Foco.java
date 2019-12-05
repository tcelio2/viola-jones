package br.com.reconhecedor.imagem.principal;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Foco {

	public static void main(String[] args) {

		
		int[] number = new int[2];
		number[0] = 12;
		number[1] = 2;
		number[2] = 33;
		//System.out.println("-->"+number[0]);
		
//		int width = 128;
//		int height = 128;
//		BufferedImage image = null;
//		File f = null;
//		
//		try {
//			f = new File("/home/hal9000/Pictures/tree.jpg");
//			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//			image = ImageIO.read(f);
//			int p = image.getRGB(122, 64);
//			int a = (p>>24) & 0xff; //alpha
//			int r = (p>>16) & 0xff; //red
//			int g = (p>>8) & 0xff; //red
//			int b = p & 0xff; //red
//			System.out.println("Leitura completa! Pixel: "+p+"\n--Alpha: "+a+"\n--Red: "+r+"\n--Green: "+g+"\n--Blue: "+b+"\n");
//		} catch (Exception e) {
//			System.out.println("Erro na leitura: "+e.getCause());
//		}
//		
	}

}
