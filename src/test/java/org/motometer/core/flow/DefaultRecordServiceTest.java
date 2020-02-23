package org.motometer.core.flow;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.motometer.core.dao.RecordDao;

class DefaultRecordServiceTest {

    @Test
    void save() {
        final RecordDao recordDao = Mockito.mock(RecordDao.class);
        final Record record = Mockito.mock(Record.class);
        final DefaultRecordService recordService = new DefaultRecordService(recordDao);

        recordService.save(1L, record);

        verify(recordDao).save(1L, record);
    }
}
