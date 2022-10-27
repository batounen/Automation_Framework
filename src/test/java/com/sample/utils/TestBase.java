package com.sample.utils;

import com.sample.pages.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public Login login;
    public Home home;
    public HowToUsePinbar pinbar;
    public FleetModule fleetModule;
    public ActivitiesModule activitiesModule;
    public CalendarEvents calendarEvents;
    public CreateCalendarEvent createCalendarEvent;
    public VehicleOdometer vehicleOdometer;
    public CustomerAccounts customerAccounts;
    public VehihcleCosts vehihcleCosts;
    public Marketing marketing;
    public MarketingCampaigns marketingCampaigns;

    @BeforeClass
    public void setup() {
        Driver.getDriver(Driver.getProperty("browser"));
    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }
}