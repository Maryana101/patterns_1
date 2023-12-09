package ru.netology;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    @Value
    public static class UserData {
        private String firstLastName;
        private String phoneNumber;
        private String city;
    }

    private DataGenerator() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    private static String DateToStr(Date date, String pattern) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String getMeetingDate() {
        return DateToStr(faker.date().future(15, 3, TimeUnit.DAYS), "dd.MM.yyyy");
    }

    public static String getFirstLastName() {
        String[] fullName = faker.name().fullName().split(" ");
        return fullName[0] + " " + fullName[1];
    }

    public static String getRandomCity() {
        Random random = new Random();
        String[] city = {"Москва", "Санкт-Петербург", "Казань", "Майкоп", "Екатеринбург", "Томск", "Омск",
                "Новосибирск", "Тюмень", "Смоленск", "Чита"};
        return city[random.nextInt(city.length)];
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {

        }

        public static UserData generateUser() {
            return new UserData(getFirstLastName(), getPhoneNumber(), getRandomCity());
        }
    }
}
