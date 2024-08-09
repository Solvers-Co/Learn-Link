package co.solvers.apilearnlink.api.controller.usuario;

import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.reacao.ReacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioLoginDto;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioCriacaoRequestDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemRankingDto;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;
    private final ReacaoService reacaoService;

    @Operation(summary = "Autenticar um usuário", description = "Método que autentica um usuário", tags = {"Usuários"})
    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {

        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @Operation(summary = "Desconectar um usuário", description = "Método que desconecta um usuário", tags = {"Usuários"})
    @ApiResponse(responseCode = "200", description = "Usuário desconectado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PostMapping("/logout/{id}")
    public ResponseEntity<UsuarioTokenDto> desconectar(
            @PathVariable
            @Parameter(name = "id", description = "Usuario id", example = "1") Long id){
        UsuarioTokenDto usuarioToken = usuarioService.desconectar(id);

        return ResponseEntity.status(200).body(usuarioToken);
    }

    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "409", description = "E-mail do usuario já foi cadastrado")
    @Operation(summary = "Criar um usuário", description = "Método que cria um usuário", tags = {"Usuários"})
    @PostMapping
    public ResponseEntity<UsuarioListagemDto> criarUsuario(
            @RequestBody @Valid UsuarioCriacaoRequestDto dto) {

        Usuario usuarioCadastrado = usuarioService.criar(UsuarioMapper.toEntity(dto), dto.getEscolaridadeId());
        UsuarioListagemDto listagemDto = UsuarioMapper.toUsuarioListagemResponseDto(usuarioCadastrado);
        return ResponseEntity.status(201).body(listagemDto);
    }

    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @Operation(summary = "Obtém um usuario pelo id", description = "Método que obtém um usuario pelo id", tags = {"Usuários"})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioListagemDto> listarPorId(
            @PathVariable
            @Parameter(name = "id", description = "Usuario id", example = "1") Long id) {

        Usuario usuario = usuarioService.buscarPorId(id);
        UsuarioListagemDto dto = UsuarioMapper.toUsuarioListagemResponseDto(usuario);
        return ResponseEntity.status(200).body(dto);
    }

    @ApiResponse(responseCode = "204", description = "Usuário deletado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @Operation(summary = "Deletar um usuário", description = "Método que deleta um usuário", tags = {"Usuários"})
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioListagemDto> deletarUsuarioPorId(
            @PathVariable
            @Parameter(name = "id", description = "Usuario id", example = "1") Long id) {

        usuarioService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @ApiResponse(responseCode = "200", description = "Senha do usuário atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @Operation(summary = "Atualizar senha", description = "Método que atualiza senha do usuário", tags = {"Usuários"})
    @PatchMapping("/atualizar-senha/{id}")
    public ResponseEntity<UsuarioListagemDto> atualizarSenhaUsuarioPorId(
            @PathVariable
            @Parameter(name = "id", description = "Usuario id", example = "1") Long id,
            @RequestParam
            @Parameter(name = "senha", description = "Senha usuario", example = "12345") String senha
    ) {

        Usuario usuarioNovo = usuarioService.atualizar(id, senha);
        UsuarioListagemDto dto = UsuarioMapper.toUsuarioListagemResponseDto(usuarioNovo);
        return ResponseEntity.status(200).body(dto);
    }

    @ApiResponse(responseCode = "200", description = "Status do usuário alterado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @Operation(summary = "Mudar status do usuário", description = "Método que muda status do usuário", tags = {"Usuários"})
    @PatchMapping("/{id}/status/{idTipoStatus}")
    public ResponseEntity<UsuarioListagemDto> alterarStatus(
            @PathVariable
            @Parameter(name = "id", description = "Usuario id", example = "1") Long id,
            @PathVariable
            @Parameter(name = "idTipoStatus", description = "Tipo status id", example = "2") Integer idTipoStatus){
        Usuario usuarioAnalisado = usuarioService.alterarStatus(id, idTipoStatus);
        UsuarioListagemDto dto = UsuarioMapper.toUsuarioListagemResponseDto(usuarioAnalisado);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/tipo-status")
    public ResponseEntity<List<UsuarioListagemDto>> listarUsuariosTipoStatus (@RequestParam String tipoStatus){
        List<Usuario> usuarios = usuarioService.listarUsuariosTipoStatus(tipoStatus);

        if (usuarios.isEmpty()) return ResponseEntity.noContent().build();

        List<UsuarioListagemDto> usuariosDto = UsuarioMapper.toUsuarioListagemResponseDto(usuarios);

        return ResponseEntity.ok(usuariosDto);
    }


    @GetMapping("/ranking")
    public ResponseEntity<List<UsuarioListagemRankingDto>> ranking(){

        List<UsuarioListagemRankingDto> usuarios = usuarioService.ranking();
//        List<UsuarioListagemRankingDto> dtos = UsuarioMapper.toUsuarioListagemRankingDto(usuarios);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar-todos-os-usuarios")
    public ResponseEntity<List<UsuarioAceitacaoListagemDto>> listagemDeUsuarios(){

        List<UsuarioAceitacaoListagemDto> usuarios = usuarioService.listagemDeUsuarios();

        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios-ativos")
    public ResponseEntity<List<UsuarioAceitacaoListagemDto>> listagemDeUsuariosAtivos(){

        List<UsuarioAceitacaoListagemDto> usuarios = usuarioService.listagemDeUsuariosAtivos();

        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios-pendentes")
    public ResponseEntity<List<UsuarioAceitacaoListagemDto>> listagemDeUsuariosPendentes(){

        List<UsuarioAceitacaoListagemDto> usuarios = usuarioService.listagemDeUsuariosPendentes();

        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios-negados")
    public ResponseEntity<List<UsuarioAceitacaoListagemDto>> listagemDeUsuariosNegados(){

        List<UsuarioAceitacaoListagemDto> usuarios = usuarioService.listagemDeUsuariosNegados();

        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }
}
