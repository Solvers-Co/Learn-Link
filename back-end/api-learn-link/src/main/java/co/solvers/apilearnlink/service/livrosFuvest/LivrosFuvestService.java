package co.solvers.apilearnlink.service.livrosFuvest;

import co.solvers.apilearnlink.service.livrosFuvest.dto.LivrosFuvestCriacaoDto;
import co.solvers.apilearnlink.service.livrosFuvest.dto.mapper.LivrosFuvestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivrosFuvestService {


    public List<LivrosFuvestCriacaoDto> lerArquivoTxt() {
        BufferedReader bufferedReader = null;
        List<LivrosFuvestCriacaoDto> listaLivros = new ArrayList<>();
        String registro;
        long nextId = 1;

        try {
            // Caminho do arquivo na pasta raiz do projeto
            Path path = Paths.get("livros_fuvest.txt");
            bufferedReader = new BufferedReader(new FileReader(path.toFile()));

            while ((registro = bufferedReader.readLine()) != null) {
                String tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    // Header
                    System.out.println("Header do arquivo de livros:");
                    if (registro.length() >= 11) {
                        System.out.println("Tipo de arquivo: " + registro.substring(2, 11).trim());
                    } else {
                        System.out.println("Registro de header inválido: " + registro);
                    }
                } else if (tipoRegistro.equals("01")) {
                    // Registro de dados (livros)
                    try {
                        if (registro.length() >= 6) {
                            LivrosFuvestCriacaoDto livro = new LivrosFuvestCriacaoDto();

                            livro.setId(nextId++); // Atribui o próximo ID e incrementa
                            livro.setAnoVestibular(Integer.parseInt(registro.substring(2, 6).trim()));
                            livro.setTitulo(registro.substring(6, Math.min(106, registro.length())).trim());

                            // Verifica se o registro tem espaço suficiente para o autor
                            if (registro.length() >= 106) {
                                livro.setAutor(registro.substring(106).trim());
                            } else {
                                livro.setAutor("Autor desconhecido");
                            }

                            listaLivros.add(livro);
                        } else {
                            System.out.println("Registro de livro inválido: " + registro);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao parsear registro de livro: " + e.getMessage());
                    }
                } else if (tipoRegistro.equals("02")) {
                    // Trailer
                    if (registro.length() >= 13) {
                        System.out.println("Trailer:");
                        System.out.println("Quantidade total de registros: " + registro.substring(2, 13).trim());
                    } else {
                        System.out.println("Registro de trailer inválido: " + registro);
                    }
                } else {
                    System.out.println("Registro inválido: " + registro);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
            }
        }

        return listaLivros;
    }

    protected BufferedReader createBufferedReader(Path path) throws IOException {
        return new BufferedReader(new FileReader(path.toFile()));
    }


}