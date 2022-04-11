package com.demo.oms.util.Converter;

import com.demo.oms.dto.BookingDTO;
import com.demo.oms.entity.Booking;
import org.modelmapper.ModelMapper;

public class BookingConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Booking convertDtoToEntity(BookingDTO bookingDto) {
        return modelMapper.map(bookingDto, Booking.class);
    }
}
