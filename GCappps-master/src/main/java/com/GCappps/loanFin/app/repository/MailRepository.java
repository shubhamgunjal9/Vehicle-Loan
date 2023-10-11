package com.GCappps.loanFin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GCappps.loanFin.app.model.SimpleMail;

@Repository
public interface MailRepository extends JpaRepository<SimpleMail, String>{

}
