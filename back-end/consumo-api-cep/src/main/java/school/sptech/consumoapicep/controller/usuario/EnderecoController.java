package school.sptech.consumoapicep.controller.usuario;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import school.sptech.consumoapicep.endereco.dto.EnderecoApiExternaDto;

public class EnderecoController {
    public static EnderecoApiExternaDto buscarEndereco(String cep) {

        RestClient client = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws/")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String raw = client.get()
                .uri(cep + "/json")
                .retrieve()
                .body(String.class);

        /*log.info("Resposta da API: " + raw);*/

        EnderecoApiExternaDto endereco = client.get()
                .uri(cep + "/json")
                .retrieve()
                .body(EnderecoApiExternaDto.class);

        if (endereco == null) {
            return null;
        }

       /* EnderecoListagemResponseDto resposta = new EnderecoListagemResponseDto();
        resposta.setBairro(endereco.getBairro());
        resposta.setCep(endereco.getCep());
        resposta.setCidade(endereco.getCidade());
        resposta.setEstado(endereco.getEstado());
        resposta.setRua(endereco.getRua());*/

        return endereco;
    }
}
