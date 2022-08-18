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
        ticketDAO = new TicketDAO();
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(new Date());
        ticket.setOutTime(new Date());
        ticketDAO.saveTicket(ticket);
        assertEquals(ticket.getVehicleRegNumber(),ticketDAO.getTicket("ABCDEF").getVehicleRegNumber());
    }
    @Test
    public void updateTicketTest() {
        ticketDAO = new TicketDAO();
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(new Date());
        ticket.setOutTime(new Date());
        ticketDAO.saveTicket(ticket);
        ticket = ticketDAO.getTicket("ABCDEF");
        int newPrice = 2500;
        ticket.setPrice(newPrice);
        ticketDAO.updateTicket(ticket);
        ticket = ticketDAO.getTicket("ABCDEF");
        assertEquals(ticket.getPrice(),newPrice);
    }
    @Test
    public void getTicketByIdTest() {
        ticketDAO = new TicketDAO();
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(new Date());
        ticket.setOutTime(new Date());
        ticketDAO.saveTicket(ticket);
        ticket = ticketDAO.getTicket("ABCDEF");
        assertEquals("ABCDEF",ticketDAO.getTicketById(ticket.getId()).getVehicleRegNumber());
    }
}
