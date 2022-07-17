package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

/**
 * Calculate the fare when the car or bike gets out of the parking, regarding the rate per hour
 * Under 30 minutes , the parking is gratis
 */
public class FareCalculatorService {

    public void calculateFare(Ticket ticket,boolean recurrentUser){
        double duration;

        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ) {
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
        long lduration = (outHour - inHour) / 60000;
        duration = lduration / 60.0;
        if (duration<=0.50) {
            ticket.setPrice(Fare.RATE_UNDER_HALF_HOUR);
        } else {
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                price=duration * Fare.CAR_RATE_PER_HOUR;
                break;
            }
            case BIKE: {
                price=duration * Fare.BIKE_RATE_PER_HOUR;
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
        }
        if (recurrentUser==true) {
            price= 0.095 * price;
            ticket.setPrice(price);
        } else{
            ticket.setPrice(price);
        }
    }
}