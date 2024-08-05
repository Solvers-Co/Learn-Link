package school.sptech.consumoapicep.controller.usuario;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.consumoapicep.ListaEstatica;
import school.sptech.consumoapicep.domain.endereco.Endereco;
import school.sptech.consumoapicep.domain.usuario.Usuario;
import school.sptech.consumoapicep.endereco.dto.EnderecoApiExternaDto;
import school.sptech.consumoapicep.endereco.dto.mapper.EnderecoMapper;
import school.sptech.consumoapicep.usuario.dto.UsuarioCriacaoRequestDto;
import school.sptech.consumoapicep.usuario.dto.UsuarioListagemResponseDto;
import school.sptech.consumoapicep.usuario.dto.mapper.UsuarioMapper;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

   /* @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EnderecoRepository enderecoRepository;*/

    static ListaEstatica<Usuario> cadastros = new ListaEstatica(5);

    @PostMapping
    public ResponseEntity<UsuarioListagemResponseDto> cadastrar(@RequestBody @Valid UsuarioCriacaoRequestDto usuarioNovo){
       EnderecoApiExternaDto enderecoApi = EnderecoController.buscarEndereco(usuarioNovo.getCep());
       Endereco endereco = EnderecoMapper.toEntity(enderecoApi, usuarioNovo.getNumero());
       /*Endereco enderecoCadastrado = enderecoRepository.save(endereco);*/

       Usuario entity = UsuarioMapper.toEntity(usuarioNovo, endereco);

       /*Usuario usuarioCadastrado = usuarioRepository.save(entity);*/

       if (cadastros.isFull()) return ResponseEntity.status(429).build();

       cadastros.adiciona(entity);

       UsuarioListagemResponseDto dto = UsuarioMapper.toDto(entity);

       return ResponseEntity.ok(dto);
   }

   @GetMapping
   public ResponseEntity<ListaEstatica> listarUsuarios (){

       if(cadastros.isEmpty()) return ResponseEntity.noContent().build();

       return ResponseEntity.ok(cadastros);
   }

   @GetMapping("/ordena")
    public ListaEstatica ordenaUsuariosPorCep(){
      /* if (cadastros.isFull() || cadastros.isEmpty()) return ResponseEntity.badRequest().build();*/


           Usuario aux = null;

           for (int i = 0 ; i < cadastros.getNroElem()-1 ; i++){
               for (int x = 1; x < cadastros.getNroElem() - i; x++){

                   if (Integer.parseInt(cadastros.get(x-1).getEndereco().getCep().replaceAll("[^0-9]", "")) > Integer.parseInt(cadastros.get(x).getEndereco().getCep().replaceAll("[^0-9]", ""))){
                       aux = cadastros.get(x);
                       cadastros.set(x, cadastros.get(x-1));
                       cadastros.set(x-1, aux);
                   }
               }
           }


       /*cadastros.ordenaVetorPorCep();*/
       return cadastros;
   }

   @GetMapping("/pesquisa-cep")
   public ResponseEntity<UsuarioListagemResponseDto> pesquisaUsuarioPorCep (@RequestParam String cep){

           int iInicio = 0;
           int iFim = cadastros.getNroElem()-1;
           int indicePesquisa = -1;

           int x = Integer.parseInt(cep.replaceAll("[^0-9]", ""));

           while (iInicio <= iFim){

               int iMeio = (iInicio+iFim)/2;

               if (Integer.parseInt(cadastros.get(iMeio).getEndereco().getCep().replaceAll("[^0-9]", "")) == x) {
                   indicePesquisa = iMeio;

                   UsuarioListagemResponseDto dto = UsuarioMapper.toDto(cadastros.get(indicePesquisa));

                   return ResponseEntity.ok(dto);
               } else if (x < Integer.parseInt(cadastros.get(iMeio).getEndereco().getCep().replaceAll("[^0-9]", ""))) {
                   iFim = iMeio-1;
               } else  {
                   iInicio = iMeio+1;
               }
           }

         /*  return -1;*/


       /*int indice = cadastros.pesquisaBinaria(cep);*/

       return ResponseEntity.notFound().build();



   }

   @PostMapping("/cadastra-mock")
    public ResponseEntity<Void> cadastraMock(){
       Endereco end1 = new Endereco(1, "Rua dos Bobos", 10, "Jardim dos Jardins", "São Paulo", "SP", "74987380");
       Endereco end2 = new Endereco(2, "Avenida da Paz", 352, "Higienópolis", "São Paulo", "SP", "65606070");
       Endereco end3 = new Endereco(3, "Rua Clemente Andrade Reis", 5, "Jardim Império", "São Paulo", "SP", "28020720");
       Endereco end4 = new Endereco(4, "Rua Professor José Augusto", 191, "Tucuruvi", "São Paulo", "SP", "19312133");

       Usuario user1 = new Usuario(1, "Pedro Silva", "pedro.silva@gmail.com", "pedro123", "", 0, end1);
       Usuario user2 = new Usuario(2, "Ana Oliveira", "ana.oliveira@gmail.com", "ana123", "", 0, end2);
       Usuario user3 = new Usuario(3, "José Roberto", "jose.roberto@gmail.com", "jose123", "", 0, end3);
       Usuario user4 = new Usuario(4, "Paula Cunha", "paula.cunha@gmail.com", "paula123", "", 0, end4);
       cadastros.adiciona(user1);
       cadastros.adiciona(user2);
       cadastros.adiciona(user3);
       cadastros.adiciona(user4);

       return ResponseEntity.ok().build();
   }






}
