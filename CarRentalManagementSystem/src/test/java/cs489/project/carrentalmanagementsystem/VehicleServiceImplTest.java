package cs489.project.carrentalmanagementsystem;

import cs489.project.carrentalmanagementsystem.dto.vehicle.response.VehicleResponse;
import cs489.project.carrentalmanagementsystem.exception.VehicleException;
import cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleStatus;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleType;
import cs489.project.carrentalmanagementsystem.repository.VehicleRepository;
import cs489.project.carrentalmanagementsystem.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getVehicleById_Found() {
        // Setup
        Long id = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setRentalRate(100.0);
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicle.setMake("Toyota");
        vehicle.setModel("Camry");
        vehicle.setYear(2020);
        vehicle.setMileage(1000L);
        vehicle.setColor("Black");
        vehicle.setType(VehicleType.SEDAN);
        vehicle.setVin("1234567890");
        vehicle.setPlateNumber("123456");
        vehicle.setRentalAgreements(null);
        vehicle.setReservations(null);

        VehicleResponse expectedResponse = new VehicleResponse(); // Assuming VehicleResponse is a valid DTO class
        expectedResponse.setId(id);
        expectedResponse.setColor("Black");
        expectedResponse.setMake("Toyota");
        expectedResponse.setModel("Camry");
        expectedResponse.setMileage(1000L);
        expectedResponse.setPlateNumber("123456");
        expectedResponse.setRentalRate(100.0);
        expectedResponse.setType(VehicleType.SEDAN);
        expectedResponse.setVehicleStatus(VehicleStatus.AVAILABLE);
        expectedResponse.setVin("1234567890");
        expectedResponse.setYear(2020);


        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));
        when(modelMapper.map(vehicle, VehicleResponse.class)).thenReturn(expectedResponse);

        // Execution
        VehicleResponse actualResponse = vehicleService.getVehicleById(id);

        // Assertion
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(vehicleRepository).findById(id);
        verify(modelMapper).map(vehicle, VehicleResponse.class);
    }

    @Test
    public void getVehicleById_NotFound() {
        // Setup
        Long id = 1L;
        when(vehicleRepository.findById(id)).thenReturn(Optional.empty());

        // Execution & Assertion
        assertThrows(VehicleException.class, () -> {
            vehicleService.getVehicleById(id);
        });

        verify(vehicleRepository).findById(id);
        verify(modelMapper, never()).map(any(), eq(VehicleResponse.class));
    }
}