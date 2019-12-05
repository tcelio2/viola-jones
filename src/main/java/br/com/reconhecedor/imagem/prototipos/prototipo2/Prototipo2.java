package br.com.reconhecedor.imagem.prototipos.prototipo2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import Jama.SingularValueDecomposition;
import br.com.reconhecedor.imagem.principal.Parametros;

public class Prototipo2 {
	
	public final static String PATH = Parametros.PATH;
	
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

	static List<Double> listaDistanciasTreinamento = new ArrayList<>();

	static Double threshold = 1000000000000000000000000000000000000000000000000.0;

	static List<File> fotosList = new ArrayList<>();

	//static File f1  = new File("/home/hal9000/Pictures/ReconhecimentoFacial/editada/teste1.jpg");
	static File f1  = new File(PATH+"foto1.jpg");
	static File f2  = new File(PATH+"foto2.jpg");
	static File f3  = new File(PATH+"foto8.jpg");
	static File f4  = new File(PATH+"foto9.jpg");
	static File f5  = new File(PATH+"foto13.jpg");
	static File f6  = new File(PATH+"foto14.jpg");
	static File f7  = new File(PATH+"foto15.jpg");
	static File f8  = new File(PATH+"foto16.jpg");
	static File f9  = new File(PATH+"foto18.jpg");
	static File f10 = new File(PATH+"foto20.jpg");

	static File fotoTeste = new File(PATH+"foto20.jpg");

	public static void main(String[] args) throws IOException {
		fotosList.add(f1);
		fotosList.add(f2); fotosList.add(f3); fotosList.add(f4); fotosList.add(f5);
		fotosList.add(f6); fotosList.add(f7); 
		fotosList.add(f8); fotosList.add(f9); 
		fotosList.add(f10);

		iniciarAprendizado();

		Threshold.criarListaThreshold(fotosList);
		threshold = Threshold.encontrarThresholdBaseadoNaLista();

		verificarFotoPertenceAoConjunto(fotoTeste);



	}

	/**
	 * FASE DE TREINAMENTO
	 * 
	 * 
	 * 
	 * 
	 */
	private static void iniciarAprendizado() throws IOException {

		matrizPrincipal = ImagemUtils.montarMatrixPadrao(fotosList);
		media = ImagemUtils.calcularMedia(matrizPrincipal);
		matrizAssinatura = ImagemUtils.calcularMatrizAssinatura(fotosList);

		
		//AQUI É O CALCULO DE CONVARIÂNCIA || Aqui so podemos usar a biblioteca da Apache!!! :/
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
		//FotoUtils.remontarFoto(array);

		System.out.println("FIM DA FASE TREINAMENTO!!!");
	}

	
	
	private static void verificarFotoPertenceAoConjunto(File fotoTeste2) throws IOException {
		//VERIFICAR SE FOTO 20 PERTENCE AO GRUPO DE FOTOS
		///A)
		montarMatrixPadrao_verificacao(fotoTeste2, 0);
		Matrix media_ = new Matrix(media);//10000 x 1
		//obtencao da matrix da foto a ser analisada
		Matrix fotoTeste = new Matrix(matrizPrincipal_procurada);//10000 x 1
		
		System.out.println(media_.getColumnDimension()+"--"+media_.getRowDimension());
		System.out.println(fotoTeste.getColumnDimension()+"--"+fotoTeste.getRowDimension());
		
		Matrix assinatura_local = fotoTeste.minusEquals(media_);//10000 x 1
		Matrix eigenfaceTransposta_ = new Matrix(eigenfaceTransposta);
		Matrix matriz_peso_foto_procurada = eigenfaceTransposta_.times(assinatura_local); //10 x 1 <---
		
		///B)
		Matrix eigenfacePrincipal_ = new Matrix(eigenfacePrincipal);//10000x10
		Matrix a1 = eigenfacePrincipal_.times(matriz_peso_foto_procurada);//10000x1
		Matrix fotoTesteReconstruida = a1.plus(media_);//10000x1
		
		
		
		//Matrix assinaturas                 = new Matrix(matrizAssinatura); //10000x10
		//		Matrix matrix_eigenface_transposta = new Matrix(eigenfaceTransposta);//10x10000
		//		Matrix matriz_peso = matrix_eigenface_transposta.times(assinaturas);//10x10

		//		Matrix assinatura_geral = new Matrix(matrizAssinatura); //10000 x 10
		//Matrix matrix_eigenface_transposta = new Matrix(eigenfaceTransposta); //10 x 10000
		//Matrix matriz_peso = matrix_eigenface_transposta.times(assinaturas); //10 x 10
		//System.out.println(matriz_peso.getRowDimension()+"--"+matriz_peso.getColumnDimension());

		double dist = 0;
		for (int i = 1; i < NUMERO_LINHAS; i++) {
			Matrix matrix1 = fotoTeste.getMatrix(0, 9, 0, 0);
			Matrix matrix2 = fotoTesteReconstruida.getMatrix(0, NUMERO_LINHAS - 1, i-1, i-1);

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
					matrizPrincipal_procurada[k][numeroFoto] = ImagemUtils.getPixelNormalizado(p2);
					k++;
				}
			}
		}catch(Exception e){
			System.out.println("Erro: "+e.getCause());
		}
		System.out.println("Matriz de média calculada ==>"+ media_procurada[0][0]+"-->"+matrizPrincipal_procurada[0][0]);

	}

}
