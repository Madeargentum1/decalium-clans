package com.manya.clans.storage.converters;

import com.manya.clans.storage.converters.uuid.UuidMapper;
import com.manya.clans.clan.member.ClanMember;
import com.manya.clans.clan.role.ClanRole;
import net.kyori.adventure.util.Index;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanMemberMapper implements RowMapper<ClanMember> {
    private static final String UUID = "uuid", NAME = "name", ROLE = "role";
    private final Index<String, ClanRole> roles;

    public ClanMemberMapper(Index<String, ClanRole> roles) {
        this.roles = roles;
    }
    @Override
    public ClanMember map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new ClanMember(
                UuidMapper.INSTANCE.map(rs, UUID, ctx),
                rs.getString(NAME),
                roles.value(rs.getString(ROLE))
        );
    }
}
