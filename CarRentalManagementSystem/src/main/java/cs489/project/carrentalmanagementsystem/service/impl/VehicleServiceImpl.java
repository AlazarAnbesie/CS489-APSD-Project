package cs489.project.carrentalmanagementsystem.service.impl;

import cs489.project.carrentalmanagementsystem.dto.vehicle.request.VehicleRequest;
import cs489.project.carrentalmanagementsystem.dto.vehicle.response.VehicleResponse;
import cs489.project.carrentalmanagementsystem.exception.VehicleException;
import cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleStatus;
import cs489.project.carrentalmanagementsystem.repository.VehicleRepository;
import cs489.project.carrentalmanagementsystem.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<VehicleResponse> getAllVehicle() {
        return vehicleRepository.findAll()
                .stream()
                .map(reservation -> modelMapper.map(reservation, VehicleResponse.class))
                .toList();
    }

    @Override
    public VehicleResponse getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(()->new VehicleException("Vehicle with id "+id+" is not found"));
        return modelMapper.map(vehicle, VehicleResponse.class);
    }

    @Override
    public VehicleResponse createVehicle(VehicleRequest vehicle) {
        return modelMapper.map(vehicleRepository.save(modelMapper.map(vehicle, Vehicle.class)), VehicleResponse.class);
    }

    @Override
    public VehicleResponse updateVehicle(VehicleRequest vehicle, Long id) {
        Vehicle vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(()->new VehicleException("Vehicle with id "+id+" is not found"));
        vehicleEntity.setMake(vehicle.getMake());
        vehicleEntity.setModel(vehicle.getModel());
        vehicleEntity.setYear(vehicle.getYear());
        vehicleEntity.setMileage(vehicle.getMileage());
        vehicleEntity.setVin(vehicle.getVin());
        vehicleEntity.setColor(vehicle.getColor());
        vehicleEntity.setPlateNumber(vehicle.getPlateNumber());
        vehicleEntity.setVehicleStatus(vehicle.getVehicleStatus());
        vehicleEntity.setType(vehicle.getType());
        vehicleEntity.setRentalRate(vehicle.getRentalRate());
        return modelMapper.map(vehicleRepository.save(vehicleEntity), VehicleResponse.class);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.findById(id)
                .orElseThrow(()->new VehicleException("Vehicle with id "+id+" is not found"));
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleResponse> getAvailableVehicles() {
        return vehicleRepository.findAllByVehicleStatus(VehicleStatus.AVAILABLE)
                .stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleResponse.class))
                .toList();
    }

    @Override
    public VehicleRequest setVehicleBooked(Long id) {
        Vehicle vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(()->new VehicleException("Vehicle with id "+id+" is not found"));
        vehicleEntity.setVehicleStatus(VehicleStatus.BOOKED);
        return modelMapper.map(vehicleRepository.save(vehicleEntity), VehicleRequest.class);
    }

    @Override
    public VehicleResponse setVehicleRentalRate(Long id, Double rentalRate) {
        Vehicle vehicleEntity = vehicleRepository.findById(id)
                .orElseThrow(()->new VehicleException("Vehicle with id "+id+" is not found"));
        vehicleEntity.setRentalRate(rentalRate);
        return modelMapper.map(vehicleRepository.save(vehicleEntity), VehicleResponse.class);
    }
}
