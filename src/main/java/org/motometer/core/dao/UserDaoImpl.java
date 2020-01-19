package org.motometer.core.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.motometer.core.dao.generated.tables.TelegramUsers;
import org.motometer.core.dao.generated.tables.records.TelegramUsersRecord;
import org.motometer.core.service.model.ImmutableUser;
import org.motometer.core.service.model.User;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
class UserDaoImpl implements UserDao {

    private final AtomicInteger integer = new AtomicInteger(1);

    private final DataSource dataSource;

    @Override
    public void saveOrUpdate(User user) {
        DSLContext using = DSL.using(dataSource, SQLDialect.POSTGRES);

        TelegramUsersRecord record = new TelegramUsersRecord();

        record.setId(integer.incrementAndGet());
        record.setTelegramUserId(user.telegramUserId().intValue());
        record.setFirstName(user.firstName());
        record.setLastName(user.lastName());
        record.setLanguageCode(user.languageCode());
        record.setIsBot(user.isBot());

        using.executeInsert(record);
    }

    @Override
    public Optional<User> findByUserId(long userId) {
        DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);

        Result<Record> fetch = create.select(TelegramUsers.TELEGRAM_USERS.fields())
            .from(TelegramUsers.TELEGRAM_USERS)
            .where(TelegramUsers.TELEGRAM_USERS.TELEGRAM_USER_ID.eq((int) userId))
            .limit(1)
            .fetch();

        return StreamEx.of(fetch)
            .map(v -> {
                TelegramUsersRecord record = new TelegramUsersRecord();
                record.from(v);
                return record;
            })
            .<User>map(v -> ImmutableUser.builder()
                .id(v.getId().longValue())
                .userName(v.getUsername())
                .firstName(v.getFirstName())
                .lastName(v.getLastName())
                .isBot(v.getIsBot())
                .languageCode(v.getLanguageCode())
                .telegramUserId(v.getTelegramUserId().longValue())
                .build()).findFirst();

    }
}
