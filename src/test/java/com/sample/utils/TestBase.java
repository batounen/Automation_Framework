package com.sample.utils;

import com.sample.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public ActivitiesModule activitiesModule;
    public CalendarEvents calendarEvents;
    public Calls calls;
    public CreateCalendarEvent createCalendarEvent;
    public CustomerAccounts customerAccounts;
    public FleetModule fleetModule;
    public Home home;
    public HowToUsePinbar pinbar;
    public Login login;
    public Marketing marketing;
    public MarketingCampaigns marketingCampaigns;
    public VehihcleCosts vehihcleCosts;
    public VehicleOdometer vehicleOdometer;


    @BeforeClass
    public void setup() {
        Driver.getDriver(Driver.getProperty("browser"));
    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }
}