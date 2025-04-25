package com.taskStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskStore.entity.Attendance;
import com.taskStore.repository.attendanceRepository;

@Service
public class attendanceService {

    @Autowired
    private attendanceRepository attendanceRepo;

    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }
}
