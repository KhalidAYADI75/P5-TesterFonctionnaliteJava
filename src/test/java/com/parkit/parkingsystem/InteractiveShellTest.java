package com.parkit.parkingsystem;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.InteractiveShell;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InteractiveShellTest {

    private InteractiveShell interactiveShell;

    @BeforeEach
    private void setUpPerTest() {
        interactiveShell = new InteractiveShell();
    }

    @Test
    public void loadInterfaceProcessIncomingVehicleTest() {
        when(interactiveShell.inputReaderUtil.readSelection()).thenReturn(1);
        interactiveShell.loadInterface();
        verify(interactiveShell.parkingService, Mockito.times(1)).processIncomingVehicle();
    }
}
