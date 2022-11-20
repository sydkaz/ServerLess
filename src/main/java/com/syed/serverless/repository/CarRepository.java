package com.syed.serverless.repository;

import com.syed.serverless.model.Car;
import com.syed.serverless.model.car_make;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class CarRepository {
    List<Car> cars;
    public CarRepository() {
        cars = IntStream.rangeClosed(1, 10)
                .peek(CarRepository::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i -> new Car(i, "Car - " + i, CarRepository.randomCarMake()))
                .collect(Collectors.toList());
    }

    private static void sleepExecution(int i){
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static car_make randomCarMake(){
        return car_make.values()[new Random().nextInt(car_make.values().length)];
    }

    public List<Car> getCars()  {
        return cars;
    }

    public List<Car> addCar(Car car)  {
        cars.add(car);
        return cars;
    }
}
