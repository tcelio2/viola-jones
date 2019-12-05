package br.com.reconhecedor.imagem.principal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class WriteNewImageList {

	final static String PATH_PHOTO = Parametros.PATH_PHOTO;
	final static String PATH_PHOTO_RESULT = Parametros.PATH_PHOTO_RESULT;
	
	public static void main(String[] args) {
		
		int m = 0;
		BufferedImage image = null;
		File f = null;
		List<Integer> xs = new ArrayList<>();
		List<Integer> ys = new ArrayList<>();

		
		
		try {
			f = new File(PATH_PHOTO);
			image = ImageIO.read(f);
				for (int i = 0; i < 128; i++) {
					for (int j = 0; j < 128; j++) {
							xs.add(i);
							ys.add(j);
							m++;
					}
				}
			}catch(Exception e){
				System.out.println("Erro: "+e.getCause());
			}
		
//	    Integer i = xs.get(15558);
//	    Integer j = ys.get(15558);
//	    
//		int p = image.getRGB(i, j);
//		int a = (p>>24) & 0x000000ff; //alpha
//		int r = (p>>16) & 0x000000ff; //red
//		int g = (p>>8) & 0x000000ff; //red
//		int b = p & 0x000000ff; //red
//		System.out.println("Pixel no: "+m+"x="+i+"j="+j+"-value:"+p+"\n--Alpha: "+a+"\n--Red: "+r+"\n--Green: "+g+"\n--Blue: "+b+"\n\n");
//		
		//BufferedImage img = null;//new BufferedImage(128, 128, BufferedImage.TYPE_4BYTE_ABGR);
		
		for (int k = 0; k < m; k++) {
			Integer value1 = xs.get(k);
			Integer value2 = ys.get(k);
			int p2 = image.getRGB(value1, value2);
			
			//exemplo abaixo funciona bem tambem!!
//			int a2 = (p2>>24) & 0xff;//alpha
//			int r2 = (p2 & 0xf80000);//red
//			int g2 = (p2 & 0xf800);//green
//			int b2 = (p2 & 0xff); //blue

			int a3 = (p2>>24)&0xff;
			int r3 = (p2>>16)&0xff;
			int g3 = (p2>>8)&0xff;
			int b3 =  p2&0xff;

			
			int avg = (b3+g3+r3)/3;
			int cu = (a3<<24) | (avg<<16) | (avg<<8) | avg;
			image.setRGB(value1, value2, cu);
		}
		File f2 = null;
		f2 = new File(PATH_PHOTO_RESULT);
		try {
			ImageIO.write(image, "jpg", f2);
		} catch (IOException e) {
			System.out.println("Erro ao criar:" + e.getCause());		
		}
	}
}
