package org.primeTimeHotel.Domain_Model_Objects;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDomainModelObject {
    protected int id;
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public abstract void setStatement(PreparedStatement statement, int parameterIndex) throws SQLException;
}
