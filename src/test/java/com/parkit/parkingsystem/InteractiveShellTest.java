package com.parkit.parkingsystem;


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

    @Mock
    private InteractiveShell interactiveShell;

    @Mock
    private InputReaderUtil inputReaderUtil;

    @Mock
    private ParkingService parkingService;

    @BeforeEach
    private void setUpPerTest() {
        interactiveShell = new InteractiveShell();
        interactiveShell.setInputReaderUtil(inputReaderUtil);
        interactiveShell.setParkingService(parkingService);
    }

    @Test
    public void loadInterfaceProcessIncomingVehicleTest() {
        when(inputReaderUtil.readSelection()).thenReturn(1, 2,3);
        when(parkingService.processIncomingVehicle()).thenReturn(new Ticket());
        when(parkingService.processExitingVehicle()).thenReturn(new Ticket());
        interactiveShell.loadInterface();
        verify(interactiveShell.parkingService, Mockito.times(1)).processIncomingVehicle();
        verify(interactiveShell.parkingService, Mockito.times(1)).processExitingVehicle();

    }
}
