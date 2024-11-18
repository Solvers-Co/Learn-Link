package co.solvers.apilearnlink.service.tipostatus;


import co.solvers.apilearnlink.domain.tipostatus.repository.TipoStatusRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TipoStatusServiceTest {

    @InjectMocks
    private TipoStatusService service;

    @Mock
    private TipoStatusRepository repository;
}
