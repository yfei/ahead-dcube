/*
 *  Copyright (c) Mr.Yang 2012-2016 All Rights Reserved
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.dcube.ahead.jpa.fei.query.filter.impl;


import java.util.Arrays;
import java.util.List;

import cn.dcube.ahead.jpa.fei.query.filter.operator.ConditionOperator;


/**
 * less than or equal filter
 */
public class LTEFilter extends BaseFilter {

    private VariableFilter variable;

    private ConstantFilter constant;


    private LTEFilter(VariableFilter variable, ConstantFilter constant) {
        this.variable = variable;
        this.constant = constant;
    }

    public LTEFilter(String variable, Object constant) {
        this(new VariableFilter(variable), new ConstantFilter(constant));
    }


    @Override
    public String getString() {
        return ConditionOperator.LT_E.getValue().replace("%0", variable.getString()).replace("%1", "?");
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

