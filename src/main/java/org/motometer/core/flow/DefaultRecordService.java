package org.motometer.core.flow;

import org.motometer.core.dao.RecordDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultRecordService implements RecordService {

    private final RecordDao recordDao;

    @Override
    public void save(final Long userId, final Record record) {
        recordDao.save(userId, record);
    }
}
