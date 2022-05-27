package cn.dcube.ahead.jpa.fei.query.filter.operator;


public enum ConditionOperator {

    AND("(%0) and (%1)"),
    OR("(%0) or (%1)"),
    EQUAL("%0=%1"),
    NOT_EQUAL("%0<>%1"),
    IN("%0 in (%1)"),
    NOT_IN("%0 not in (1%)"),
    BETWEEN("%0 between %1 and %2"),
    EXISTS("%0 exists (%1)"),
    GT("%0 > (%1)"),
    GT_E("%0 >= (%1)"),
    LT("%0 < (%1)"),
    LT_E("%0 <= (%1)"),
    LIKE("%0 like %1");

    private String value;

    ConditionOperator(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.print(ConditionOperator.valueOf(">=").getValue());
    }
}
