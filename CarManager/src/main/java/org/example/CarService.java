package org.example;

import java.util.List;

public interface CarService {
    Car getCar(int id);
    void updateCar(Car car);
    void deleteCar(int id);
    List<Car> getAllCars();
}
