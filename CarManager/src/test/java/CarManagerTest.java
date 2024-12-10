import org.example.Car;
import org.example.CarManager;
import org.example.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CarManagerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarManager carManager;

    @ParameterizedTest
    @MethodSource("paramsForTestBoundary")
    public void testGetCarNameById_BoundaryValues(int carId, String expectedCarName) {
        System.out.println("Testing with Car ID: " + carId);

        if (carId == 1) {
            when(carService.getCar(1)).thenReturn(new Car("Toyota", 1));
        } else if (carId == 1000) {
            when(carService.getCar(1000)).thenReturn(new Car("Mercedes", 1000));
        }
        String carName = carManager.getCarNameById(carId);
        System.out.println("Returned car name for ID " + carId + ": " + carName);

        assertEquals(expectedCarName, carName);
    }

    private static Stream<Arguments> paramsForTestBoundary() {
        return Stream.of(
                Arguments.of(0, null), // Alsó határ alatt
                Arguments.of(1, "Toyota"), //Alsó határon belül
                Arguments.of(1000, "Mercedes"), //Felső határon belül
                Arguments.of(1001, null) //Felső határon kívül
        );
    }

    @Test
    public void testGetCarNameById(){
        System.out.println("Test 4: testGetCarNameById");

        when (carService.getCar(1)).thenReturn(new Car("Toyota", 1));

        String actual = carManager.getCarNameById(1);
        String expected = "Toyota";

        System.out.println("Expected car name: " + expected + ", Actual car name: " + actual);
        assertEquals(expected, actual);

        verify(carService).getCar(1);
    }

    @Test
    public void testUpdateCarName(){
        System.out.println("Test 5: testUpdateCarName");

        when(carService.getCar(1)).thenReturn(new Car("Honda", 1));

        carManager.updateCarName(1, "Tesla");

        System.out.println("Updated car name to 'Tesla' for ID 1.");
        verify(carService).getCar(1);
        verify(carService).updateCar(argThat(car -> car.getName().equals("Tesla")));
    }

    @Test
    public void testGetAllCars(){
        System.out.println("Test 6: testGetAllCars");

        when(carService.getAllCars()).thenReturn(Arrays.asList(
                new Car("Ford", 1),
                new Car("BMW", 2)
        ));

        List<Car> cars = carManager.getAllCars();
        System.out.println("Number of cars retrieved: " + cars.size());
        cars.forEach(car -> System.out.println("Car: " + car.getName() + ", ID: " + car.getId()));

        int expected = 2;
        int actual = cars.size();

        assertEquals(expected, actual);
        verify(carService).getAllCars();
    }

    @Test
    public void testGetAllCars_EmptyList() {
        System.out.println("Test 8: testGetAllCars_EmptyList");

        when(carService.getAllCars()).thenReturn(Arrays.asList());

        List<Car> cars = carManager.getAllCars();
        System.out.println("Number of cars retrieved: " + cars.size());

        int expected = 0;
        int actual = cars.size();

        assertEquals(expected, actual);
        verify(carService).getAllCars();
    }


    @Test
    public void testDeleteCar(){
        System.out.println("Test 7: testDeleteCar");

        carManager.deleteCar(1);
        System.out.println("Deleted car with ID 1.");

        verify(carService).deleteCar(1);
    }
}
