import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Leitor {
    public static void main(String[] args) {

        File diretorioArquivosPendentes = new File("C:\\Users\\natal\\Desktop\\Desafio\\Pastas\\Pendentes");
        File diretorioArquivosValidados = new File("C:\\Users\\natal\\Desktop\\Desafio\\Pastas\\Validado");
        File diretorioArquivosInvalidados = new File("C:\\Users\\natal\\Desktop\\Desafio\\Pastas\\Invalidados");

        System.out.println("Diretório Raiz: " + diretorioArquivosPendentes.getName());

        for (File arquivo : diretorioArquivosPendentes.listFiles()) {
            boolean isAValidFile = true;
            if (arquivo.isFile() && arquivo.getName().endsWith("csv")) {
                Scanner scanner = null;

                //Abrir o arquivo
                try {
                    scanner = new Scanner(arquivo);

                    while (scanner.hasNextLine()) {
                        String linha = scanner.nextLine();
                        String[] colunas = linha.split(";");

                        //verifica se o arquivo é válido
                        if (linha.isEmpty() || (!linha.equals(";;;") && colunas.length != 4)) {
                            isAValidFile = false;
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                    if (scanner != null) {
                        scanner.close();
                    }
                }
            }
            if (isAValidFile) {
                arquivo.renameTo(new File(diretorioArquivosValidados, arquivo.getName()));
            } else {
                arquivo.renameTo(new File(diretorioArquivosInvalidados, arquivo.getName()));
            }
        }
    }
}
