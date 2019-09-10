package com.ilinksolutions.p3m1.data;

import java.util.List;
import com.ilinksolutions.p3m1.domains.UKVisaMessage;

/**
 *
 */
public interface UKVisaDAO
{
	UKVisaMessage save(UKVisaMessage entry);
    List<UKVisaMessage> list();
    UKVisaMessage getEntry(int id);
    UKVisaMessage updateEntry(int id, UKVisaMessage message);
}
