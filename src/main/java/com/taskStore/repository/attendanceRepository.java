package com.taskStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskStore.entity.Attendance;

public interface attendanceRepository extends JpaRepository<Attendance,Long>{
    @Query("SELECT a FROM Attendance a JOIN FETCH a.employee WHERE a.employeeId = :employeeId")
    List<Attendance> findByEmployeeId(@Param("employeeId") Long employeeId);
}
