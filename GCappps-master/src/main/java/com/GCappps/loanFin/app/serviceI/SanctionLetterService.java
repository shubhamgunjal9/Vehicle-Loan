package com.GCappps.loanFin.app.serviceI;

import com.GCappps.loanFin.app.model.Customer;
import com.GCappps.loanFin.app.model.SanctionLetter;

public interface SanctionLetterService {
	
	public SanctionLetter generatesanction(SanctionLetter sanctionLetter, String cutomerId);

}
