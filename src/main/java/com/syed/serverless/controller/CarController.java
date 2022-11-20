package com.syed.serverless.controller;

import com.syed.serverless.model.Car;
import com.syed.serverless.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//@Configuration
@Component
@ApplicationScope()
public class CarController {
    @Autowired
    CarRepository carRepository;

    @Bean
    public Function<Integer, Car> getCarByIdFunction(){
        return value -> {
            System.out.println("Received request for car id: "+value);
            return carRepository.getCars().stream().filter( car -> car.getId() == value).findAny().orElse(null);
        };
    }

    @Bean
    public Consumer<Car> addCar(){
        return value -> {
            System.out.println("Received request for car: "+value);
            carRepository.addCar(value);
        };
    }

    @Bean
    public Supplier<Flux<Car>> getAllCars(){
        return () -> {
            return Flux.fromIterable(carRepository.getCars().stream().collect(Collectors.toList()));
        };
    }
}
