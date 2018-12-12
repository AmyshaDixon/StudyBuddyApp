package com.example.adixon.studyapp;

public class StudySession {
    private int ID;
    private String topic; // Study topic user inputs
    private String measurement; // Pages, chapters, times
    private String frequency; // Everyday, every week, every month, etc.
    private Integer duration; // Number of hours/minutes user inputs
    private String durationType; // Minutes/ hours
    private String weekDay; // The day the user chooses if they chose "every week" for frequency
    private String monthlyDate; // The specific date a user chooses if the pick "every month" for freq.
    private String color; // The color the user chooses for the calendar highlight/text

    public StudySession(String topic, String measurement, String frequency) { // Duration + durType go together
        this.topic = topic;
        this.measurement = measurement;
        this.frequency = frequency;

        /* Set default values for variables that aren't needed */
        this.duration = 0;
        this.durationType = null;
        this.weekDay = null;
        this.monthlyDate = null;
        color = null;
    }

    public StudySession(String topic, String measurement, String frequency, Integer duration,
                        String durationType) {
        this.topic = topic;
        this.measurement = measurement;
        this.frequency = frequency;
        this.duration = duration;
        this.durationType = durationType;

        /* Set default values for variables that aren't needed */
        this.weekDay = null;
        this.monthlyDate = null;
        color = null;
    }

    public StudySession(String topic, String measurement, String frequency, Integer duration,
                        String durationType, String weekDay) {
        this.topic = topic;
        this.measurement = measurement;
        this.frequency = frequency;
        this.duration = duration;
        this.durationType = durationType;
        this.weekDay = weekDay;

        /* Set default values for variables that aren't needed */
        this.monthlyDate = null;
        color = null;
    }

    public StudySession(String topic, String measurement, String frequency, Integer duration,
                        String durationType, String weekDay, String monthlyDate) {
        this.topic = topic;
        this.measurement = measurement;
        this.frequency = frequency;
        this.duration = duration;
        this.durationType = durationType;
        this.weekDay = weekDay;
        this.monthlyDate = monthlyDate;

        /* Set default values for variables that aren't needed */
        color = null;
    }

    public StudySession(String topic, String measurement, String frequency, Integer duration,
                        String durationType, String weekDay, String monthlyDate, String color) {
        this.topic = topic;
        this.measurement = measurement;
        this.frequency = frequency;
        this.duration = duration;
        this.durationType = durationType;
        this.weekDay = weekDay;
        this.monthlyDate = monthlyDate;
        this.color = color;
    }

    public String getTopic() {
        return topic;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getFrequency() {
        return frequency;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDurationType() {
        return durationType;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public String getMonthlyDate() {
        return monthlyDate;
    }

    public String getColor() {
        return color;
    }
}
