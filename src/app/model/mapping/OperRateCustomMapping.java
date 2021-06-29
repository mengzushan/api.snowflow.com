package app.model.mapping;

import org.jfaster.mango.mapper.AbstractRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperRateCustomMapping extends AbstractRowMapper<OperRate>{

    @Override
    public OperRate mapRow(ResultSet rs, int i) throws SQLException {
        OperRate o = new OperRate();
        o.setSourceId(rs.getString("Cid"));
        o.setOperRateId(rs.getString("Oid"));
        o.setDestId(rs.getString("sid"));
        o.setCreateTime(rs.getTime("Otime"));
        o.setNote(rs.getString("note"));
        o.setMountMoney(rs.getDouble("Omoney"));
        o.setState(rs.getInt("state"));
        return o;
    }
}
