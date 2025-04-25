package com.taskStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskStore.entity.taskList;

@Repository
public interface taskRepository extends JpaRepository<taskList,Integer>  {

}
