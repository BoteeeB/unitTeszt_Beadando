package org.example;

import java.util.List;

public class CarManager {

    private final CarService carService;

    public CarManager(CarService carService){
        this.carService = carService;
    }

    public String getCarNameById(int id){
        if (id > 0 && id <= 1000){
            Car car = carService.getCar(id);
            return car != null ? car.getName() : null;
        }
        return null;

    }

    public void updateCarName(int id, String newName){
        Car car = carService.getCar(id);
        if (car != null){
            car.setName(newName);
            carService.updateCar(car);
        }
    }

    public void deleteCar(int id){
        carService.deleteCar(id);
    }

    public List<Car> getAllCars(){
        return carService.getAllCars();
    }
}
