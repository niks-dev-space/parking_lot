package com.gojeck.core;

import org.testng.annotations.Test;

import com.gojek.core.SlotAllocator;
import com.gojek.entities.Car;
import com.gojek.entities.Slot;
import com.gojek.exceptions.NoFreeSlotsAvailable;
import com.gojek.exceptions.SlotIsNotYetOccupied;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class CoreTest {

    private SlotAllocator allocator;

    /*
	 * Each test assume all 6 slots are full
     */
    /**
     * @param regNumber
     * @param color
     * @throws NoFreeSlotsAvailable
     */
    @Test(dataProvider = "parkFullDP", expectedExceptions = {NoFreeSlotsAvailable.class})
    public void parkFull(String regNumber, String color) throws NoFreeSlotsAvailable {
        allocator.status();
        Slot slot = allocator.park(new Car(regNumber, color));
    }

    @DataProvider
    public Object[][] parkFullDP() {
        return new Object[][]{{"KA-8850", "black"}};
    }

    @Test()
    public void leaveTest() throws SlotIsNotYetOccupied {
        Object[][] objects = parkDP();
        for (int i = 0; i < objects.length; i++) {
            Car car = allocator.leave(i + 1);
            Assert.assertEquals(car.getRegistrationNumber(), parkDP()[i][0]);
        }
    }

    @Test(expectedExceptions = {SlotIsNotYetOccupied.class})
    public void leaveInvalidTest() throws SlotIsNotYetOccupied {
        Car car = allocator.leave(1);
        car = allocator.leave(1);
    }

    @Test()
    public void leaveThenParkTest() throws SlotIsNotYetOccupied, NoFreeSlotsAvailable {
        Car car = allocator.leave(1);
        Slot slot = allocator.park(new Car("KA-1234", "white"));
        Assert.assertEquals(slot.getSlotNumber(), new Integer(1));
    }

    @BeforeMethod
    public void beforeTest() throws NoFreeSlotsAvailable {
        allocator = new SlotAllocator();
        allocator.createSlots(6);
        Object[][] objects = parkDP();
        for (int i = 0; i < objects.length; i++) {
            Slot slot = allocator.park(new Car(objects[i][0].toString(), objects[i][1].toString()));
            Assert.assertEquals(slot.getSlotNumber(), objects[i][2]);
        }
    }

    @DataProvider
    public Object[][] parkDP() {
        return new Object[][]{{"KA-0909", "white", 1}, {"KA-8880", "black", 2}, {"KA-8810", "black", 3},
        {"KA-8820", "black", 4}, {"KA-8830", "black", 5}, {"KA-8840", "black", 6}};
    }

    @AfterMethod
    public void afterTest() throws SlotIsNotYetOccupied {
        allocator = null;
    }

    @BeforeSuite
    public void beforeSuite() {
    }

    @AfterSuite
    public void afterSuite() {
    }

}
