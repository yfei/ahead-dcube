package cn.ahead.dcube.jpa.fei.query.filter.impl;


import java.util.Arrays;
import java.util.List;

import cn.ahead.dcube.jpa.fei.query.filter.operator.ConditionOperator;


/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 14:11 <br/>
 * Version:1.0 <br/>
 */
public class EqualFilter extends BaseFilter {

    private VariableFilter variable;

    private ConstantFilter constant;

    private EqualFilter(VariableFilter variable, ConstantFilter constant) {
        this.variable = variable;
        this.constant = constant;
    }

    public EqualFilter(String variable, Object constant) {
        this(new VariableFilter(variable),new ConstantFilter(constant));
    }


    @Override
    public String getString() {
       return  ConditionOperator.EQUAL.getValue().replace("%0",variable.getString()).replace("%1","?");
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
