package br.com.reconhecedor.imagem.prototipos.prototipo1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import br.com.reconhecedor.imagem.principal.Parametros;

public class Prototipo1 {
	
	public static final String PATH_PHOTO = Parametros.PATH;
	public static final String PATH_PHOTO_RESULT = Parametros.PATH_PHOTO_RESULT;

	static final Integer NUMERO_FOTOS = 10;
	static final Integer TAMANHO = 100;	
	static final Integer NUMERO_LINHAS = TAMANHO * TAMANHO; 
	
	static double[][] media = new double[NUMERO_LINHAS][1];
	static double[][] matrizPrincipal = new double[TAMANHO*TAMANHO][NUMERO_FOTOS];
	static double[][] matrizAssinatura = new double[TAMANHO*TAMANHO][NUMERO_FOTOS];

	static double[][] media_procurada = new double[NUMERO_LINHAS][1];
	static double[][] matrizPrincipal_procurada = new double[TAMANHO*TAMANHO][1];
	static double[][] matrizAssinatura_procurada = new double[TAMANHO*TAMANHO][NUMERO_FOTOS];
	
	static double[][] matrixDePesoPrincipalArray = new double[NUMERO_FOTOS][NUMERO_FOTOS];
	static double[][] eigenfacePrincipal = new double[NUMERO_LINHAS][NUMERO_FOTOS];
	static double[][] eigenfaceTransposta = new double[NUMERO_FOTOS][NUMERO_LINHAS];
	
	
//	static int[][] foto1 = new int[NUMERO_LINHAS][1];
//	static int[][] foto2 = new int[NUMERO_LINHAS][1];
//	static int[][] foto3 = new int[NUMERO_LINHAS][1];
//	static int[][] foto4 = new int[NUMERO_LINHAS][1];
//	static int[][] foto5 = new int[NUMERO_LINHAS][1];
//	static int[][] foto6 = new int[NUMERO_LINHAS][1];
//	static int[][] foto7 = new int[NUMERO_LINHAS][1];
//	static int[][] foto9 = new int[NUMERO_LINHAS][1];
//	static int[][] foto10 = new int[NUMERO_LINHAS][1];
	
	//static List<int[][]> fotos = new ArrayList<>();
	
	///static int valor;

	static List<Double> listaDistanciasTreinamento = new ArrayList<>();
	
	static Double threshold = 1000000000000000000000000000000000000000000000000.0;
	//static Double distanciaImagemTeste = 0.0;
	
	//static List<int[][]> listaAssinaturas = new ArrayList<>();
	
	static List<File> fotosList = new ArrayList<>();

	//static File f1  = new File("/home/hal9000/Pictures/ReconhecimentoFacial/editada/teste1.jpg");
	static File f1  = new File(PATH_PHOTO+"foto1.jpg");
	static File f2  = new File(PATH_PHOTO+"foto2.jpg");
	static File f3  = new File(PATH_PHOTO+"foto8.jpg");
	static File f4  = new File(PATH_PHOTO+"foto9.jpg");
	static File f5  = new File(PATH_PHOTO+"foto13.jpg");
	static File f6  = new File(PATH_PHOTO+"foto14.jpg");
	static File f7  = new File(PATH_PHOTO+"foto15.jpg");
	static File f8  = new File(PATH_PHOTO+"foto16.jpg");
	static File f9  = new File(PATH_PHOTO+"foto18.jpg");
	static File f10 = new File(PATH_PHOTO+"foto20.jpg");
	
	static File fotoTeste = new File(PATH_PHOTO+"foto20.jpg");

	public static void main(String[] args) throws IOException {
		
//		double[][] media1 = new double[2][2];
//		media1[0][0] = 0;
//		media1[0][1] = 1;
//		media1[1][0] = -2;
//		media1[1][1] = -3;
//		
//		Matrix ma1 = new Matrix(media1);
//		EigenvalueDecomposition eig = ma1.eig();
//		double[][] array = eig.getD().getArray();
//		double[][] array2 = eig.getV().getArray();
//		Matrix d = eig.getD();
//		Matrix v = eig.getV();
//		Matrix minus = d.minusEquals(v);
//		
//		double[] imagEigenvalues = eig.getImagEigenvalues();
//		double[] realEigenvalues = eig.getRealEigenvalues();
	
		//------------
//		double[][] media2 = new double[3][3];
//		media2[0][0] = 0;
//		media2[0][1] = -1;
//		media2[0][2] = 0;
//		media2[1][0] = 1;
//		media2[1][1] = -1;
//		media2[1][2] = 1;
//		media2[2][0] = -1;
//		media2[2][1] = -1;
//		media2[2][2] = 1;
//		
//		Matrix ma2 = new Matrix(media2);
//		EigenvalueDecomposition eig2 = ma2.eig();
//		double[][] array_ = eig2.getD().getArray();
//		SingularValueDecomposition svd = eig2.getV().svd();
//		Matrix d2 = eig2.getD();
//		Matrix v2 = eig2.getV();
//		Matrix minus2 = d2.minusEquals(v2);
//		
//		double[] imagEigenvalues2 = eig2.getImagEigenvalues();
//		double[] realEigenvalues2 = eig2.getRealEigenvalues();
		
		
		
		
		fotosList.add(f1);
		fotosList.add(f2); fotosList.add(f3); fotosList.add(f4); fotosList.add(f5);
		fotosList.add(f6); fotosList.add(f7); 
		fotosList.add(f8); fotosList.add(f9); 
		fotosList.add(f10);

		iniciarAprendizado();
		
		criarListaThreshold();
		encontrarThresholdBaseadoNaLista();

		verificarFotoPertenceAoConjunto(fotoTeste);
		
		
		
	}


	private static void verificarFotoPertenceAoConjunto(File fotoTeste2) throws IOException {
		//VERIFICAR SE FOTO 20 PERTENCE AO GRUPO DE FOTOS
		montarMatrixPadrao_verificacao(fotoTeste2, 0);
		Matrix media_local = new Matrix(media);//10000 x 1
		//obtencao da matrix da foto a ser analisada
		Matrix matriz_teste = new Matrix(matrizPrincipal_procurada);//10000 x 1 
		
		Matrix assinatura_local = media_local.minus(matriz_teste);//10000 x 1
		//Matrix eigenfaceTransposta_local = new Matrix(eigenfaceTransposta);
		//Matrix matriz_peso_foto_procurada = eigenfaceTransposta_local.times(assinatura_local); //10 x 1		
		//---------------------------------------
		Matrix assinaturas                 = new Matrix(matrizAssinatura); //10000x10
//		Matrix matrix_eigenface_transposta = new Matrix(eigenfaceTransposta);//10x10000
//		Matrix matriz_peso = matrix_eigenface_transposta.times(assinaturas);//10x10

//		Matrix assinatura_geral = new Matrix(matrizAssinatura); //10000 x 10
		//Matrix matrix_eigenface_transposta = new Matrix(eigenfaceTransposta); //10 x 10000
		//Matrix matriz_peso = matrix_eigenface_transposta.times(assinaturas); //10 x 10
		//System.out.println(matriz_peso.getRowDimension()+"--"+matriz_peso.getColumnDimension());
		
		double dist = 0;
		for (int i = 1; i < NUMERO_FOTOS; i++) {
			//Matrix matrix1 = assinatura_local.getMatrix(0, 9, 0, 0);
			Matrix matrix2 = assinaturas.getMatrix(0, NUMERO_LINHAS - 1, i-1, i-1);

			Matrix diferenca = assinatura_local.minus(matrix2);
			
			
			for (int j = 0; j < NUMERO_LINHAS; j++) {
				dist += Math.pow(diferenca.get(j, 0), 2);
				//System.out.println(minus.get(j, 0));
			}
			//RealMatrix subtract = ma.subtract(mb);
			//dist += Math.pow(distanciaEncontrada[k][0], 2); 
			//System.out.println("--quadrado>"+dist);
			//System.out.println("+++"+minus.getArray());
		}
		double sqrt = Math.sqrt(dist);
		if(sqrt < threshold) {
			System.out.println("IMAGEM É UM FACE!");
		} else {
			System.out.println("IMAGEM NAO É UMA FACE!");
		}
		//listaDistanciasTreinamento.add(sqrt);

	}

	private static void montarMatrixPadrao_verificacao(File file, int numeroFoto) throws IOException {
		BufferedImage image = ImageIO.read(file);
		int k = 0;
		try {
			for (int i = 0; i < TAMANHO; i++) {
				for (int j = 0; j < TAMANHO; j++) {

					int p2 = image.getRGB(i, j);
					matrizPrincipal_procurada[k][numeroFoto] = getPixelNormalizado(p2);
					k++;
				}
			}
		}catch(Exception e){
			System.out.println("Erro: "+e.getCause());
		}
		System.out.println("Matriz de média calculada ==>"+ media_procurada[0][0]+"-->"+matrizPrincipal_procurada[0][0]);

	}
	
	
	
	
	
	
	
	
	
	/**
	 * FASE DE TREINAMENTO
	 * 
	 * 
	 * 
	 * 
	 */
	private static void iniciarAprendizado() throws IOException {

		for (int i = 0; i < fotosList.size(); i++) {
			montarMatrixPadrao(fotosList.get(i), i);
		}
		
		calcularMedia();

		for (int i = 0; i < fotosList.size(); i++) {
			calcularMatrizPrincipalAssinatura(fotosList.get(i), i);
		}
		
						//AQUI É O CALCULO DE CONVARIÂNCIA
						Covariance cov = new Covariance(matrizAssinatura);
						RealMatrix covarianceMatrix = cov.getCovarianceMatrix();
						double[][] covarianceMatrixFinal = covarianceMatrix.getData();
						System.out.println("FINAL DO CALCULO DE CONVARIANCIA!!");

						
						//AQUI TEMOS O CALCULO DAS EIGENVECTORS
						Matrix m = new Matrix(covarianceMatrixFinal);
						EigenvalueDecomposition m2 = m.eig();
						Matrix eigenvectorMatrix = m2.getV();
						double[][] eigenvector = eigenvectorMatrix.getArray(); //10x10
						//EigenvalueDecomposition ei = new EigenvalueDecomposition(m);
						//Matrix d = ei.getD();
						//Matriarg0x v = ei.getV();
						System.out.println("FINAL DO CALCULO DE EIGENVECTOR E EIGENVALUES");

						
						//AQUI CALCULAMOS O VALOR DE EIGENFACES
						RealMatrix ma = new Array2DRowRealMatrix(matrizAssinatura);
					    RealMatrix mb = new Array2DRowRealMatrix(eigenvector);
						RealMatrix multiply = ma.multiply(mb);
						double[][] dataEigenface = multiply.getData(); //10x10
						
//						Matrix ma_ = new Matrix(matrizAssinatura);
//						System.out.println(ma_.getColumnDimension()+"--"+ma_.getRowDimension());
//						Matrix mb_ = new Matrix(eigenvector);
//						System.out.println(mb_.getColumnDimension()+"--"+mb_.getRowDimension());
//						double[][] array = mb_.times(ma_).getArray();
						
						System.out.println("FINAL DO CALCULO DO CALCULO DE EIGENFACES!!!");
						
						
						//AQUI CALCULO DO PESO
						//RealMatrix matrixEigenface = new Array2DRowRealMatrix(dataEigenface); 
						//eigenfacePrincipal = matrixEigenface.getData();
						//RealMatrix transposeEigenface = matrixEigenface.transpose();
						//eigenfaceTransposta = transposeEigenface.getData();
						
						Matrix matrixEigenface_ = new Matrix(dataEigenface);
						eigenfacePrincipal = matrixEigenface_.getArray(); //10000 x 10
						eigenfaceTransposta = matrixEigenface_.transpose().getArray();//10000x10
						Matrix matrixAssinatura_ = new Matrix(matrizAssinatura);
						Matrix eigenfaceTransposta_ = new Matrix(eigenfaceTransposta);
						Matrix matrixDePesoPrincipal = eigenfaceTransposta_.times(matrixAssinatura_);//10 x 10000 * 10000 x 10
						matrixDePesoPrincipalArray = matrixDePesoPrincipal.getArray();//10x10
						
						Matrix media_ = new Matrix(media);//10000x1
						Matrix assinatura_ = new Matrix(matrizAssinatura);//10000x10
						
						Matrix matrix = media_.getMatrix(0, NUMERO_LINHAS-1, 0, 0);
						Matrix matrix2 = assinatura_.getMatrix(0, NUMERO_LINHAS-1, 4, 4);
						
						double[][] array = matrix.plus(matrix2).getArray();
						//remontarFoto(matrixAssinatura_.getMatrix(0, NUMERO_LINHAS-1, 0, 0).getArray());
						remontarFoto(array);
						
						System.out.println("FIM DA FASE TREINAMENTO!!!");
	}

	private static void criarListaThreshold() {
		//precisamos do eigenfaces e da matriz
		//matrizAssinatura
				Matrix assinaturas                 = new Matrix(matrizAssinatura); //10000x10
				Matrix matrix_eigenface_transposta = new Matrix(eigenfaceTransposta);//10x10000
				Matrix matriz_peso = matrix_eigenface_transposta.times(assinaturas);//10x10
				
				for (int i = 1; i < NUMERO_FOTOS; i++) {
					double dist = 0;
					Matrix matrix1 = matriz_peso.getMatrix(0, NUMERO_FOTOS-1, i, i);
					Matrix matrix2 = matriz_peso.getMatrix(0, NUMERO_FOTOS-1, i-1, i-1);
					Matrix minus = matrix2.minus(matrix1);
					
					for (int j = 0; j < NUMERO_FOTOS; j++) {
						dist += Math.pow(minus.get(j, 0), 2);
					}
					double sqrt = Math.sqrt(dist);
					listaDistanciasTreinamento.add(sqrt);
				}
				System.out.println("FIM DA FASE DE ENCONTRAR THRESHOLD==>"+listaDistanciasTreinamento);
	}
	
	private static void encontrarThresholdBaseadoNaLista() {
		listaDistanciasTreinamento.forEach(c -> {
			if(c < threshold) {
				threshold = c;
				System.out.println("mudou");
			}
		});
		threshold = threshold * 0.8;
	}
	
	private static void calcularMatrizPrincipalAssinatura(File file, Integer numeroFoto) throws IOException {

		BufferedImage image = ImageIO.read(file);	
		Integer k = 0;
		try {
			for (int i = 0; i < TAMANHO; i++) {
				for (int j = 0; j < TAMANHO; j++) {

					int p2 = image.getRGB(i, j);
					matrizAssinatura[k][numeroFoto] = getPixelNormalizado(p2) - media[k][0];
					k++;
					//System.out.println(i+"--"+j);
				}
			}
		}catch(Exception e){
			System.out.println("Erro: "+e.getMessage());
		}
		Matrix n = new Matrix(matrizAssinatura);
		System.out.println(n.getRowDimension()+"-+-"+n.getColumnDimension());

		System.out.println("Matriz de assinatura ==>"+ media[0][0]+"-->"+matrizPrincipal[0][0]);
	}

	
	private static void montarMatrixPadrao(File file, int numeroFoto) throws IOException {
		BufferedImage image = ImageIO.read(file);
		int k = 0;
		try {
			for (int i = 0; i < TAMANHO; i++) {
				for (int j = 0; j < TAMANHO; j++) {

					int p2 = image.getRGB(i, j);
					matrizPrincipal[k][numeroFoto] = getPixelNormalizado(p2);
					k++;
				}
			}
		}catch(Exception e){
			System.out.println("Erro: "+e.getCause());
		}
		System.out.println("Matriz de média calculada ==>"+ media[0][0]+"-->"+matrizPrincipal[0][0]);

	}
	

	private static void calcularMedia() throws IOException {
		//BufferedImage image = ImageIO.read(file);
		try {
			for (int i = 0; i < NUMERO_LINHAS; i++) {
				for (int j = 0; j < NUMERO_FOTOS; j++) {

					media[i][0] += matrizPrincipal[i][j];
					if(j == NUMERO_FOTOS - 1) {
						media[i][0] = media[i][0] / NUMERO_FOTOS;
					}
				}
			}
		}catch(Exception e){
			System.out.println("Erro: "+e.getCause()+"-"+e.getMessage());
		}
		System.out.println("Matriz de média calculada!");

	}



	/**
	 * Trata o pixel e normaliza para um pixel sem cores e padronizado
	 * pixel cinza
	 * 
	 */
	private static Integer getPixelNormalizado(Integer p2) {
		//	System.out.println(p2);
		int a3 = (p2>>24)&0xff;
		int r3 = (p2>>16)&0xff;
		int g3 = (p2>>8)&0xff;
		int b3 =  p2&0xff;
		int avg = (b3+g3+r3)/3;
		int pixel = (a3<<24) | (avg<<16) | (avg<<8) | avg;
		return pixel;
	}


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
	
	public static void remontarFoto(double[][] foto2) {
		int m = 0;
		int width = TAMANHO;
		int height = TAMANHO;
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		
		double[][] foto = renormalizePhoto(foto2);
		
		
		for (int k = 0; k < TAMANHO; k++) {
			for (int k2 = 0; k2 < TAMANHO; k2++) {
				int a2 = ((int)foto[k][k2]>>24) & 0xff; //alpha
				int r2 = ((int)foto[k][k2]>>16) & 0xff; //red
				int g2 = ((int)foto[k][k2]>>8) & 0xff; //green
				int b2 =  ((int)foto[k][k2]) & 0x0ff; //blue
				int bbb =  (a2<<24) | (r2<<16) | (g2<<8) | b2; //red
				System.out.println(bbb+"--");
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
		double[][] fotoReturn = new double[TAMANHO][TAMANHO];
		for (int k = 0; k < TAMANHO; k++) {
			for (int k2 = 0; k2 < TAMANHO; k2++) {
				
				fotoReturn[k][k2] = foto[counter][0];
				counter++;
			}
			
		}
		return fotoReturn;
	}
	
	
	
}
