package org.motometer.core.dao;

import org.motometer.core.flow.Record;

public interface RecordDao {

    void save(final long l, final Record record);
}
