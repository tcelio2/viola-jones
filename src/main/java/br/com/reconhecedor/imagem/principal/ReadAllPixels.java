package br.com.reconhecedor.imagem.principal;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ReadAllPixels {

	public static void main(String[] args) {

		final String PATH_PHOTO = Parametros.PATH_PHOTO;
		
		int width = 128;
		int height = 128;
		BufferedImage image = null;
		File f = null;
		
		try {
			f = new File(PATH_PHOTO);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(f);
			int m = 0;
			for (int i = 0; i < 127; i++) {
				for (int j = 0; j < 127; j++) {
					int p = image.getRGB(i, j);
					int a = (p>>24) & 0xff; //alpha
					int r = (p>>16) & 0xff; //red
					int g = (p>>8) & 0xff; //red
					int b = p & 0xff; //red
					System.out.println("Pixel no: "+m+"x="+i+"j="+j+"-value:"+p+"\n--Alpha: "+a+"\n--Red: "+r+"\n--Green: "+g+"\n--Blue: "+b+"\n\n");
					m++;
				}
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Erro na leitura: "+e.getCause());
		}
		
		
	}

}
