package com.GCappps.loanFin.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GCappps.loanFin.app.model.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger,String>{


}
