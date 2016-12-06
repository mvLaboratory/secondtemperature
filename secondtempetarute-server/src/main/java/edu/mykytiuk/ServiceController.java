package edu.mykytiuk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mykytiuk.message.Reading;
import edu.mykytiuk.message.Sensor;
import edu.mykytiuk.message.SensorGroup;
import edu.mykytiuk.message.Unit;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ServiceController {
    private final String apiURL = "https://hiring.easycoremedia.com/exam/backend/api/sensors/";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RequestMapping("/")
    public Unit getUnit(){
        RestTemplate temp = new RestTemplate();
        SSLCertificateValidation.disable();
        return temp.getForObject(apiURL, Unit.class);
    }

    @RequestMapping("/string")
    public String getStringMessage(){
        RestTemplate temp = new RestTemplate();
        SSLCertificateValidation.disable();
        return temp.getForObject(apiURL, String.class);
    }

    @RequestMapping("/temperatureOld")
    public List showTemperatureOld(){
        ArrayList<Reading> tempList = new ArrayList<>();
        Unit unit = getUnit();
        for (SensorGroup sensorGroup : unit.getMeasurements().getSensors()) {
            for (Sensor sensor :sensorGroup.getSensors()) {
                for (Reading reading: sensor.getReadings()) {
                    tempList.add(reading);
                }
            }
        }

        return tempList;
    }

    @RequestMapping("/temperature")
    public List<Reading> showTemperature() throws IOException{
        ArrayList<Sensor> sensors = new ArrayList<>();
        ArrayList<Reading> readings = new ArrayList<>();
        try {
            sensors = readSensors();
        }
        catch (IOException e) {
            return new ArrayList<>();
        }

        for (Sensor sensor : sensors) {
            if (sensor.getType().equals("temperature") && sensor.getId().equals("thermo1")) {
                readings.addAll(sensor.getReadings());
            }
        }

        return readings;
    }

    private ArrayList<Sensor> readSensors() throws IOException {
        ArrayList<Sensor> result = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        String message = getStringMessage();

        JsonNode rootNode = objectMapper.readTree(message);
        JsonNode measurementsNode = rootNode.path("measurements");
        JsonNode sensorsNode = measurementsNode.path("sensors");
        Iterator<JsonNode> elements = sensorsNode.elements();

        while(elements.hasNext()){
            JsonNode sensor = elements.next();
            JsonNode idNode = sensor.path("id");

            if (idNode.asText().contains("sensor_group")) {
                sensor = sensor.path("sensors");
                Iterator<JsonNode> sensorElements = sensor.elements();
                while(sensorElements.hasNext()){
                    JsonNode sensorElement = sensorElements.next();

                    ObjectMapper mapper = new ObjectMapper();
                    result.add(mapper.treeToValue(sensorElement, Sensor.class));
                }

            }
            else {
                ObjectMapper mapper = new ObjectMapper();
                result.add(mapper.treeToValue(sensor, Sensor.class));
            }
        }
        return result;
    }
}
