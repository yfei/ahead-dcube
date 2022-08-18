package cn.ahead.dcube.jpa.fei.query.filter.impl;

import java.util.Arrays;
import java.util.List;

import cn.ahead.dcube.jpa.fei.query.filter.operator.ConditionOperator;


/**
 * greater than or equal filter
 */
public class GTEFilter extends BaseFilter {

    private VariableFilter variable;

    private ConstantFilter constant;


    private GTEFilter(VariableFilter variable, ConstantFilter constant) {
        this.variable = variable;
        this.constant = constant;
    }

    public GTEFilter(String variable, Object constant) {
        this(new VariableFilter(variable), new ConstantFilter(constant));
    }


    @Override
    public String getString() {
        return ConditionOperator.GT_E.getValue().replace("%0", variable.getString()).replace("%1", "?");
    }

    @Override
    public List<Object> getValues() {
        return Arrays.asList(constant.getValues().get(0));
    }

    @Override
    public void setTableAlias(String alias) {
        variable.setTableAlias(alias);
    }
}

