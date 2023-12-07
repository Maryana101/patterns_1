package ru.netology;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class UserData {
        private String firstLastName;
        private String phoneNumber;
        private String city;
        private String meetingDate;

        public UserData(String firstLastName, String phoneNumber, String city, String meetingDate) {
            this.firstLastName = firstLastName;
            this.phoneNumber = phoneNumber;
            this.city = city;
            this.meetingDate = meetingDate;
        }

        public void setNewMeetingDate() {
            Faker faker = new Faker(new Locale("ru"));
            this.meetingDate = DateToStr(faker.date().future(15, 3, TimeUnit.DAYS), "dd.MM.yyyy");
        }

        public static UserData generate(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            String[] fullName = faker.name().fullName().split(" ");
            String name = fullName[0] + " " + fullName[1];
            String phoneNumber = faker.phoneNumber().phoneNumber();
            String city = getRandomCity();
            String date = DateToStr(faker.date().future(30, 3, TimeUnit.DAYS), "dd.MM.yyyy");
            return new UserData(
                    name,
                    phoneNumber,
                    city,
                    date);
        }

        private static String DateToStr(Date date, String pattern) {

            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            String strDate = dateFormat.format(date);
            return strDate;
        }

        private static String getRandomCity() {
            Random random = new Random();
            String[] city = {"Москва", "Санкт-Петербург", "Казань", "Майкоп", "Екатеринбург", "Томск", "Омск", "Новосибирск"};

            return city[random.nextInt(city.length)];
        }

        public String getFirstLastName() {
            return firstLastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getCity() {
            return city;
        }

        public String getMeetingDate() {
            return meetingDate;
        }


    }
}
