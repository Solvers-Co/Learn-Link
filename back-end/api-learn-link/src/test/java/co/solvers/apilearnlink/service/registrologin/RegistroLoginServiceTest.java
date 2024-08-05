package co.solvers.apilearnlink.service.registrologin;


import co.solvers.apilearnlink.domain.registroLogin.repository.RegistroLoginRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RegistroLoginServiceTest {

    @InjectMocks
    private RegistroLoginService service;

    @Mock
    private RegistroLoginRepository repository;

}
