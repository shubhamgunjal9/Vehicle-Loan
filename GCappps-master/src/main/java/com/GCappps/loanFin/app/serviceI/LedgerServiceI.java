package com.GCappps.loanFin.app.serviceI;

import java.util.Optional;

import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.Ledger;

public interface LedgerServiceI {

	public Ledger ledgergeneration(Customer customer);

	public Ledger payinstallment(Ledger ledger, Integer payinstallment);

	public Ledger unpayinstallment(Ledger ledger, Integer installmentnumber);

	public Optional<Ledger> getLedger(Customer customer);

}
