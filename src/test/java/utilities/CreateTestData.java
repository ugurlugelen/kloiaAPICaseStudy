package utilities;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class CreateTestData {

    public static String emailData(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String postBody(){
        Faker faker = new Faker();
        String payload = "{\n" +
                "  \"id\": "+faker.number().digits(15)+",\n" +
                "  \"username\": \""+faker.gameOfThrones().character()+"\",\n" +
                "  \"firstName\": \""+faker.address().firstName()+"\",\n" +
                "  \"lastName\": \""+faker.address().lastName()+"\",\n" +
                "  \"email\": \""+faker.internet().emailAddress()+"\",\n" +
                "  \"password\": \""+faker.internet().password()+"\",\n" +
                "  \"phone\": \""+faker.phoneNumber().phoneNumber()+"\",\n" +
                "  \"userStatus\": "+faker.number().numberBetween(0,1)+"\n" +
                "}";
        return payload;
    }

    public static Map<String, Object> setUpTestData(){
        Faker faker = new Faker();
        Map<String,Object> bodyDataMap = new HashMap<>();
        bodyDataMap.put("id",faker.number().digits(15));
        bodyDataMap.put("username",faker.gameOfThrones().character());
        bodyDataMap.put("firstName",faker.address().firstName());
        bodyDataMap.put("lastName",faker.address().lastName());
        bodyDataMap.put("email",faker.internet().emailAddress());
        bodyDataMap.put("password",faker.internet().password());
        bodyDataMap.put("phone",faker.phoneNumber().phoneNumber());
        bodyDataMap.put("userStatus",faker.number().numberBetween(0,1));

        return bodyDataMap;
    }



}
