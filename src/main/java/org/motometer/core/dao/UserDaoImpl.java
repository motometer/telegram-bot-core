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
import org.motometer.core.dao.generated.tables.TelegramUser;
import org.motometer.core.dao.generated.tables.records.TelegramUserRecord;
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
            .from(TelegramUser.TELEGRAM_USER)
            .where(TelegramUser.TELEGRAM_USER.TELEGRAM_USER_ID.eq(user.telegramUserId()))
            .fetch();

        if (exists(count)) {
            jooq.update(TelegramUser.TELEGRAM_USER)
                .set(TelegramUser.TELEGRAM_USER.FIRST_NAME, user.firstName())
                .set(TelegramUser.TELEGRAM_USER.LAST_NAME, user.lastName())
                .set(TelegramUser.TELEGRAM_USER.USERNAME, user.userName())
                .set(TelegramUser.TELEGRAM_USER.LANGUAGE_CODE, user.languageCode())
                .execute();
        } else {
            TelegramUserRecord record = new TelegramUserRecord();
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

        Result<Record> fetch = create.select(TelegramUser.TELEGRAM_USER.fields())
            .from(TelegramUser.TELEGRAM_USER)
            .where(TelegramUser.TELEGRAM_USER.TELEGRAM_USER_ID.eq(userId))
            .limit(1)
            .fetch();

        return StreamEx.of(fetch)
            .map(v -> {
                TelegramUserRecord record = new TelegramUserRecord();
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
