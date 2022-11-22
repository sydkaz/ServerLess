This is serverless example of Spring use below endpoints

curl -X GET http://localhost:8080/getCarById/1
curl -X POST http://localhost:8080/addCar/ -H "Content-Type: application/json"   -d '{"id": 200, "name": "testcar", "carMaker":"honda"}'  
curl -X GET http://localhost:8080/getAllCars/
curl -X GET http://localhost:8080/getCarById/200