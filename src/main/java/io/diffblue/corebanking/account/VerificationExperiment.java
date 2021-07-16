package io.diffblue.corebanking.account;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

public class VerificationExperiment implements Serializable,Comparable {


    private static final long someRandomNumber = 8994188029394452634L;

    private String field1;
    private BigDecimal field2;
    private Long field3;
    private BigDecimal field4;
    private BigDecimal field5;
    private String field6;
    private Date field7;

    public BigDecimal getField2() {
        return field2;
    }
    public void setField2(BigDecimal field2) {
        this.field2 = field2;
    }
    public BigDecimal getField4() {
        return field4;
    }
    public void setField4(BigDecimal field4) {
        this.field4 = field4;
    }
    public BigDecimal getField5() {
        return field5;
    }
    public void setField5(BigDecimal field5) {
        this.field5 = field5;
    }
    public String getField6() {
        return field6;
    }
    public void setField6(String field6) {
        this.field6 = field6;
    }

    public Long getField3() {
        return field3;
    }
    public void setField3(Long field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.getClass().getSimpleName() + " Object { \n" );

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            buffer.append(field.getName());
            buffer.append(" :");
            try {
                buffer.append(field.get(this));
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {
            }
            buffer.append("\n");
        }
        buffer.append("}");

        return  buffer.toString();
    }

    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Override
    public int hashCode() {
        return 0;
    }


    public boolean equals(Object obj) {
        return true;
    }

    public Date getField7() {
        return field7;
    }
    public void setField7(Date field7) {
        this.field7 = field7;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}