package br.com.reconhecedor.imagem.principal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ReadAndSaveAllPixelInArray {

	public static void main(String[] args) {

		final String PATH_PHOTO = Parametros.PATH_PHOTO;
	
		int m = 0;
		int width = 128;
		int height = 128;
		BufferedImage image = null;
		File f = null;
		List<Integer> xs = new ArrayList<>();
		List<Integer> ys = new ArrayList<>();
		
		try {
			f = new File(PATH_PHOTO);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(f);
			
			
				for (int i = 0; i < 127; i++) {
				
					for (int j = 0; j < 127; j++) {
							xs.add(i);
							ys.add(j);
							//System.out.println("Pixel no: "+m+"x="+i+"j="+j+"-value:"+p+"\n--Alpha: "+a+"\n--Red: "+r+"\n--Green: "+g+"\n--Blue: "+b+"\n\n");
							m++;
						}
					}
			}catch(Exception e){
				System.out.println("Erro: "+e.getCause());
			}
		
	    Integer i = xs.get(15558);
	    Integer j = ys.get(15558);
	    
		int p = image.getRGB(i, j);
		int a = (p>>24) & 0xff; //alpha
		int r = (p>>16) & 0xff; //red
		int g = (p>>8) & 0xff; //green
		int b = p & 0xff; //blue
		System.out.println("Pixel no: "+m+"x="+i+"j="+j+"-value:"+p+"\n--Alpha: "+a+"\n--Red: "+r+"\n--Green: "+g+"\n--Blue: "+b+"\n\n");
	}
}

		

	
		

