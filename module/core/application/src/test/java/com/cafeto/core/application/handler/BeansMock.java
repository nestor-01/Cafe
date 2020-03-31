package com.cafeto.core.application.handler;

import com.cafeto.core.application.repository.*;
import org.springframework.boot.test.mock.mockito.MockBean;

public class BeansMock {

    @MockBean
    CityRespository cityRespository;

    @MockBean
    ClinicRespository clinicRespository;

    @MockBean
    ClinicTypeRespository clinicTypeRespository;

    @MockBean
    NephrologistBaseClinicRespository nephrologistBaseClinicRespository;

    @MockBean
    NephrologistRespository nephrologistRespository;

    @MockBean
    NephrologistTypeRespository nephrologistTypeRespository;

    @MockBean
    UserRespository userRespository;

    @MockBean
    ZoneRespository zoneRespository;
}
