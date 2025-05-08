package com.admin.LoginRegisterData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.LoginRegisterData.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

}
