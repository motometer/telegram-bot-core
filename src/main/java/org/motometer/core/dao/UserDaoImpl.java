package org.motometer.core.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.motometer.core.dao.generated.Sequences;
import org.motometer.core.dao.generated.tables.TelegramUsers;
import org.motometer.core.dao.generated.tables.records.TelegramUsersRecord;
import org.motometer.core.service.model.ImmutableUser;
import org.motometer.core.service.model.User;

import javax.sql.DataSource;
import java.util.Optional;

import static org.jooq.impl.DSL.count;

@Slf4j
@RequiredArgsConstructor
class UserDaoImpl implements UserDao {

    private final DataSource dataSource;

    @Override
    public void saveOrUpdate(User user) {

        DSLContext jooq = DSL.using(dataSource, SQLDialect.POSTGRES);

        Result<Record1<Integer>> count = jooq.select(count())
            .from(TelegramUsers.TELEGRAM_USERS)
            .where(TelegramUsers.TELEGRAM_USERS.TELEGRAM_USER_ID.eq(user.telegramUserId()))
            .fetch();

        if (exists(count)) {
            jooq.update(TelegramUsers.TELEGRAM_USERS)
                .set(TelegramUsers.TELEGRAM_USERS.FIRST_NAME, user.firstName())
                .set(TelegramUsers.TELEGRAM_USERS.LAST_NAME, user.lastName())
                .set(TelegramUsers.TELEGRAM_USERS.USERNAME, user.userName())
                .set(TelegramUsers.TELEGRAM_USERS.LANGUAGE_CODE, user.languageCode())
                .execute();
        } else {
            TelegramUsersRecord record = new TelegramUsersRecord();
            record.setId(jooq.nextval(Sequences.TELEGRAM_USERS_SEQ));
            record.setTelegramUserId(user.telegramUserId());
            record.setFirstName(user.firstName());
            record.setLastName(user.lastName());
            record.setLanguageCode(user.languageCode());
            record.setIsBot(user.isBot());
            jooq.executeInsert(record);
        }
    }

    private boolean exists(Result<Record1<Integer>> fetch) {
        return StreamEx.of(fetch)
            .mapToInt(Record1::component1)
            .sum() > 0;
    }

    @Override
    public Optional<User> findByUserId(long userId) {
        DSLContext create = DSL.using(dataSource, SQLDialect.POSTGRES);

        Result<Record> fetch = create.select(TelegramUsers.TELEGRAM_USERS.fields())
            .from(TelegramUsers.TELEGRAM_USERS)
            .where(TelegramUsers.TELEGRAM_USERS.TELEGRAM_USER_ID.eq(userId))
            .limit(1)
            .fetch();

        return StreamEx.of(fetch)
            .map(v -> {
                TelegramUsersRecord record = new TelegramUsersRecord();
                record.from(v.intoMap());
                return record;
            })
            .<User>map(v -> ImmutableUser.builder()
                .id(v.getId())
                .userName(v.getUsername())
                .firstName(v.getFirstName())
                .lastName(v.getLastName())
                .isBot(v.getIsBot())
                .languageCode(v.getLanguageCode())
                .telegramUserId(v.getTelegramUserId())
                .build()).findFirst();

    }
}
