package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    private static ParkingService parkingService;

    @Mock
    private static InputReaderUtil inputReaderUtil;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO;

    @Test
    public void processExitingVehicleTest(){
        try {
            when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
            Ticket ticket = new Ticket();
            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
            ticket.setParkingSpot(parkingSpot);
            ticket.setVehicleRegNumber("ABCDEF");
            when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
            when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);
            when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);
            parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        parkingService.processExitingVehicle();
        verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
    }

    @Test
    public void processIncomingVehicleTest() {
       try {
           when(inputReaderUtil.readSelection()).thenReturn(1);
           when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
           when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
       } catch (Exception e) {
           e.printStackTrace();
           throw  new RuntimeException("Failed to set up test mock objects");
       }
       ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
       Ticket ticket=parkingService.processIncomingVehicle();
       assertNotNull(ticket);
       assertFalse(ticket.getParkingSpot().isAvailable());
    }

    @Test
    public void getNextParkingNumberIfAvailableTest(){
        try {
            when(inputReaderUtil.readSelection()).thenReturn(1);
            when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        ParkingSpot parkingSpot=parkingService.getNextParkingNumberIfAvailable();
        assertEquals(1, parkingSpot.getId());
    }

    @Test
   public void checkIfRecurrentUserTest() {
        try {
            when(parkingService.processIncomingVehicle()).thenReturn(ticketDAO.getTicket("ABCDEF"));
            when(parkingService.processExitingVehicle()).thenReturn(ticketDAO.getTicket("ABCDEF"));
            when(inputReaderUtil.readSelection()).thenReturn(1);
            when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
            ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertTrue(parkingService.checkIfRecurrentUser("ABCDEF"));
   }
}
