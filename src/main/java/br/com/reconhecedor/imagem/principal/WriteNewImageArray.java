package br.com.reconhecedor.imagem.principal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class WriteNewImageArray {

	
	final static String PATH_PHOTO = Parametros.PATH_PHOTO;
	final static String PATH_PHOTO_RESULT = Parametros.PATH_PHOTO_RESULT;
	
	
	public static void main(String[] args) { 
		int m = 0;
		int width = 128;
		int height = 128;
		BufferedImage image = null;
		File f = null;
		int[][] foto = new int[1218][128];
		//List<Integer> xs = new ArrayList<>();
		//List<Integer> ys = new ArrayList<>();
			
		try {
			f = new File(PATH_PHOTO);
			//image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			image = ImageIO.read(f);
			
				for (int i = 0; i < width - 1; i++) {
					StringBuilder stb = new StringBuilder();
					for (int j = 0; j < height - 1; j++) {
							foto[i][j] = image.getRGB(i, j); 
							int a3 = (foto[i][j]>>24)&0xff;
							int r3 = (foto[i][j]>>16)&0xff;
							int g3 = (foto[i][j]>>8)&0xff;
							int b3 =  foto[i][j]&0xff;
							
							int avg = (b3+g3+r3)/3;
							int cu = (a3<<24) | (avg<<16) | (avg<<8) | avg;
							//xs.add(i);
							//ys.add(j);
			//				System.out.println();
							//System.out.println("Pixel no: "+m+"x="+i+"j="+j+"-value:"+foto[i][j]+"\n--Alpha: "+a+"\n--Red: "+r+"\n--Green: "+g+"\n--Blue: "+b+"\n\n");
							
							stb.append(cu+" ");
							m++;
					}
					System.out.println(stb.toString());
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
		
		//BufferedImage img = null;//new BufferedImage(128, 128, BufferedImage.TYPE_4BYTE_ABGR);
		
		for (int k = 0; k < width - 1; k++) {
			for (int k2 = 0; k2 < height - 1; k2++) {
				//int p2 = image.getRGB(k, k2);
			//	if(k2 == m-1){
					//System.out.println("--"+k+"-"+k2);
				//}
				int a2 = (foto[k][k2]>>24) & 0xff; //alpha
				int r2 = (foto[k][k2]>>16) & 0xff; //red
				int g2 = (foto[k][k2]>>8) & 0xff; //green
				int b2 =  (foto[k][k2]) & 0x0ff; //blue
				int bbb =  (a2<<24) | (r2<<16) | (g2<<8) | b2; //red
			//	int bbb2 = (p2 & 0x000000ff)>>0| (p2 & 0x0000ff00)>>8 | (p2 & 0x00ff0000)>>16; //usei sem 0xff!!!
				//System.out.println(p2 & 0x000000ff);
				image.setRGB(k, k2, bbb);
			}
			//Integer value1 = xs.get(k);
			//Integer value2 = ys.get(k);
			
			
		}
		File f2 = null;
		f2 = new File(PATH_PHOTO_RESULT);
		try {
			ImageIO.write(image, "jpg", f2);
		} catch (IOException e) {
			System.out.println("Erro ao criar:" + e.getCause());		
		}
	}

		
		
	
	
	
	
 	private void sum() {
		
	}
	
}
