package co.solvers.apilearnlink.api.configuration.security;

import co.solvers.apilearnlink.api.configuration.security.jwt.GerenciadorTokenJwt;
import co.solvers.apilearnlink.service.usuario.autenticacao.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private AutenticacaoEntryPoint autenticacaoJwtEntryPoint;

    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/usuarios/login/**"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/h2-console/**/**"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/usuarios"),
            new AntPathRequestMatcher("/usuarios/publicacoes-mais-antigas"),
            new AntPathRequestMatcher("/usuarios/publicacoes-mais-recentes"),
            new AntPathRequestMatcher("/usuarios/{id}"),
            new AntPathRequestMatcher("/usuarios/{id}/status/{idTipoStatus}"),
            new AntPathRequestMatcher("/usuarios/login"),
            new AntPathRequestMatcher("/usuarios/logout/{id}"),
            new AntPathRequestMatcher("/usuarios/atualizar-senha/{id}"),
            new AntPathRequestMatcher("/usuarios/mudar-status-usuario/{id}/{idTipoStatus}"),
            new AntPathRequestMatcher("/usuarios/buscar-todos-os-usuarios-paginado"),
            new AntPathRequestMatcher("/usuarios/buscar-todos-os-usuarios-ativos-paginado"),
            new AntPathRequestMatcher("/usuarios/buscar-todos-os-usuarios-pendentes-paginado"),
            new AntPathRequestMatcher("/usuarios/buscar-todos-os-usuarios-negados-paginado"),
            new AntPathRequestMatcher("/publicacoes"),
            new AntPathRequestMatcher("/publicacoes/publicar"),
            new AntPathRequestMatcher("/publicacoes/{id}/conteudo"),
            new AntPathRequestMatcher("/publicacoes/{idPublicacao}/reagir"),
            new AntPathRequestMatcher("/publicacoes/buscar-palavra-chave"),
            new AntPathRequestMatcher("/publicacoes/{id}"),
            new AntPathRequestMatcher("/publicacoes/{idPublicacao}/comentar"),
            new AntPathRequestMatcher("/publicacoes/quantidade-de-publicacoes-em-cada-canal"),
            new AntPathRequestMatcher("/publicacoes/canal-com-maior-numero-de-publicacoes"),
            new AntPathRequestMatcher("/publicacoes//publicacoes-por-canal-paginado"),
            new AntPathRequestMatcher("/publicacoes/{idPublicacao}/remover-reacao"),
            new AntPathRequestMatcher("/publicacoes/{idPublicacao}/denunciar"),
            new AntPathRequestMatcher("/comentarios"),
            new AntPathRequestMatcher("/comentarios/{id}"),
            new AntPathRequestMatcher("/comentarios/{idComentario}/reagir"),
            new AntPathRequestMatcher("/comentarios/{idComentario}/reagir/{idReacao}"),
            new AntPathRequestMatcher("/comentarios/publicacao/{idPublicacao}"),
            new AntPathRequestMatcher("/publicacoes/quantidade-publicacoes-por-dia-mes/"),
            new AntPathRequestMatcher("/comentarios/quantidade-comentarios-por-dia-mes/"),
            new AntPathRequestMatcher("/comentarios/publicacao/{idPublicacao}/paginado"),
            new AntPathRequestMatcher("/registros/gerar-log/{id}"),
            new AntPathRequestMatcher("/qtd-materias-nao-respondidas")
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer<HttpSecurity>::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(URLS_PERMITIDAS)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(autenticacaoJwtEntryPoint))
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(new AutenticacaoProvider(autenticacaoService, passwordEncoder()));
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AutenticacaoEntryPoint jwtAuthenticationEntryPointBean() {
        return new AutenticacaoEntryPoint();
    }

    @Bean
    public AutenticacaoFilter jwtAuthenticationFilterBean() {
        return new AutenticacaoFilter(autenticacaoService, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() {
        return new GerenciadorTokenJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracao = new CorsConfiguration();
        configuracao.applyPermitDefaultValues();
        configuracao.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.TRACE.name()));

        configuracao.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));

        UrlBasedCorsConfigurationSource origem = new UrlBasedCorsConfigurationSource();
        origem.registerCorsConfiguration("/**", configuracao);

        return origem;
    }
}