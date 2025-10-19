package com.UQ.AlojaFacil.unit.Servicio;


import com.UQ.AlojaFacil.Negocio.Servicio.AnfitrionServicio;
import com.UQ.AlojaFacil.Negocio.Servicio.impl.AnfitrionServicioImpl;
import com.UQ.AlojaFacil.Negocio.dto.AnfitrionDTO;
import com.UQ.AlojaFacil.Negocio.dto.CrearAnfitrionDTO;
import com.UQ.AlojaFacil.Persistencia.dao.AnfitrionDAO;
import com.UQ.AlojaFacil.Persistencia.mapper.AnfitrionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnfitrionServicio -Unit Tests")
public class AnfitrionServicioTest {

    @Mock
    private AnfitrionDAO anfitrionDAO;

    @Mock
    private AnfitrionServicio anfitrionServicio;


    @InjectMocks
    private AnfitrionServicioImpl anfitrionServicioI;

    //Datos prueba
    private AnfitrionDTO validarAnfitrionDTO;
    private CrearAnfitrionDTO crearAnfitrionDTO;
    private Long validarIdAnfitrion;

    @BeforeEach
    void setUp(){
        validarIdAnfitrion=1L;

       crearAnfitrionDTO=new CrearAnfitrionDTO(
               "TestAnfitrion",
               "test@gmail.com",
               "312456789",
               "Test123",
               LocalDate.now()
       );
       validarAnfitrionDTO=new AnfitrionDTO(
               validarIdAnfitrion,
               crearAnfitrionDTO.getNombre(),
               crearAnfitrionDTO.getEmail(),
               crearAnfitrionDTO.getCelular(),
               crearAnfitrionDTO.getContraseña(),
               crearAnfitrionDTO.getFechaNacimiento(),
               LocalDateTime.now()
       );
    }

    //Test Crear Anfitrion
    @Test
    @DisplayName("CREATE -Anfitrion valido debe" +
            "retornar anfitrion creado con ID")
    void crateAnfitrion_ValidData_ShouldReturnCreateAnfitrion(){
        //ARRANGE (Given) -Preparar el escenario
        AnfitrionDTO expextedAnfitrion=new AnfitrionDTO(
                validarIdAnfitrion,
                validarAnfitrionDTO.getNombre(),
                validarAnfitrionDTO.getEmail(),
                validarAnfitrionDTO.getCelular(),
                validarAnfitrionDTO.getContraseña(),
                LocalDate.now(),
                LocalDateTime.now()
        );

        //Mock del comportamiento del anfitrionDAO
        when(anfitrionDAO.save(any(CrearAnfitrionDTO.class))).
                thenReturn(expextedAnfitrion);
        when(anfitrionDAO.existsByEmail(crearAnfitrionDTO.getEmail()))
                .thenReturn(false);

        AnfitrionDTO resultado=anfitrionServicioI.crearAnfitrion(crearAnfitrionDTO);

        //ASSERT(then) -Verificar los resultados
        ArgumentCaptor<CrearAnfitrionDTO>captor=ArgumentCaptor.forClass(CrearAnfitrionDTO.class);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(validarIdAnfitrion);
        assertThat(resultado.getNombre()).isEqualTo("TestAnfitrion");
        assertThat(resultado.getEmail()).isEqualTo("test@gmail.com");
        assertThat(resultado.getCelular()).isEqualTo(validarAnfitrionDTO.getCelular());

        //Verificar que se llamaron los metodos correctos
        verify(anfitrionDAO,times(1)).save(captor.capture());
        verify(anfitrionDAO,times(1)).existsByEmail(crearAnfitrionDTO.getEmail());

    }
    @Test
    @DisplayName("CREATE -Crear anfitrion con email duplicado")
    void crearAnfitrion_emailDuplicato_throws(){

        //Arrange
        CrearAnfitrionDTO crear=new CrearAnfitrionDTO();
                crear.setEmail("JuanD");
                crear.setEmail(validarAnfitrionDTO.getEmail());

        when(anfitrionDAO.existsByEmail(crear.getEmail())).thenReturn(true);

        //Act && Assert
        assertThatThrownBy(()->anfitrionServicioI.crearAnfitrion(crear))
                .isInstanceOf(IllegalArgumentException.class);

        verify(anfitrionDAO,times(1)).existsByEmail(crear.getEmail());
        verify(anfitrionDAO,never()).save(any());
    }

    @Test
    @DisplayName("CREATE -Formato invalido del email")
    void crearAnfitrion_emailFormatoInvalido_throws(){

        //Arrange
        CrearAnfitrionDTO crear=new CrearAnfitrionDTO();
        crear.setEmail("JuanD");
        crear.setEmail("email_invalido_bad");

        assertThatThrownBy(()->anfitrionServicioI.crearAnfitrion(crear))
                .isInstanceOf(IllegalArgumentException.class);

        verify(anfitrionDAO,never()).existsByEmail(anyString());
        verify(anfitrionDAO,never()).save(any());
    }

}
