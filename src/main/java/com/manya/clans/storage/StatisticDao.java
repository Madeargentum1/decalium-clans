package com.manya.clans.storage;

import com.manya.clans.clan.Clan;
import com.manya.clans.statistic.StatisticType;
import com.manya.clans.storage.converters.StatisticRow;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface StatisticDao {
    @SqlUpdate("CREATE TABLE IF NOT EXISTS stats (`clan_tag` VARCHAR(16) NOT NULL UNIQUE, `statistic_type` VARCHAR(16) NOT NULL UNIQUE, `value` INTEGER NOT NULL)")
    void createTable();
    @SqlQuery("SELECT * FROM stats")
    List<StatisticRow> getStats();

    @SqlUpdate("INSERT INTO stats (`clan_tag`, `statistic_type`, `value`) VALUES (:clan.getTag, :type.getName, :value) ON DUPLICATE KEY UPDATE `value`=:value")
    void set(@BindMethods("clan") Clan clan, @BindMethods("type") StatisticType type, @Bind("value") int value);


    @SqlUpdate("DELETE FROM stats WHERE `clan_tag`=:clan.getTag AND `statistic_type`=:type.getName")
    void remove(@BindMethods("clan") Clan clan, @BindMethods("type") StatisticType type);
}
