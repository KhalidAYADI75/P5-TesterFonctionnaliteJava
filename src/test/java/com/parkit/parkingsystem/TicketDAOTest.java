package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class TicketDAOTest {

    private InputReaderUtil inputReaderUtil;

    private ParkingSpotDAO parkingSpotDAO;
    private  TicketDAO ticketDAO;

    @Test
    public void getTicketTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        Date inTime = new Date();
        ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(inTime);
        ticket.setOutTime(null);
        ticket.setPrice(0);
        ticket.setOutTime(null);
        ticketDAO=new TicketDAO();
        ticketDAO.saveTicket(ticket);
        assertEquals(ticket.getVehicleRegNumber(),ticketDAO.getTicket("ABCDEF"));
    }
    @Test
    public void updateTicketTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        Date inTime = new Date();
        ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(inTime);
        ticket.setOutTime(null);
        ticket.setPrice(0);
        ticket.setOutTime(null);
        ticketDAO=new TicketDAO();
        ticketDAO.saveTicket(ticket);
        ticketDAO.updateTicket(ticket);
        assertNotNull(ticketDAO);
    }
    @Test
    public void getTicketByIdTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        Date inTime = new Date();
        ticket = new Ticket();
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(inTime);
        ticket.setOutTime(null);
        ticket.setPrice(0);
        ticket.setOutTime(null);
        ticketDAO=new TicketDAO();
        ticketDAO.saveTicket(ticket);
        assertEquals(ticket.getVehicleRegNumber(),ticketDAO.getTicketById(ticket.getId()));
    }
}
