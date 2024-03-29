package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.SpecialDateMapper;
import com.eparking.eparking.domain.SpecialDate;
import com.eparking.eparking.service.interf.SpecialDateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecialDateImpl implements SpecialDateService {

    private final SpecialDateMapper specialDateMapper;
    @Override
    public List<SpecialDate> getSpecialDateOfParking(int parkingID) {
        return specialDateMapper.getSpecialDateOfParking(parkingID);
    }
}
