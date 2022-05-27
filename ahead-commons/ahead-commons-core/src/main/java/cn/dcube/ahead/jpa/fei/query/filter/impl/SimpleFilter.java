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

import java.util.ArrayList;
import java.util.List;

import cn.dcube.ahead.jpa.fei.query.filter.IFilter;
import cn.dcube.ahead.jpa.fei.query.filter.operator.ConditionOperator;


/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 13:35 <br/>
 * Version:1.0 <br/>
 */
public class SimpleFilter extends BaseFilter {

	private IFilter left;

	private ConditionOperator operator;

	private IFilter right;

	public SimpleFilter(IFilter left, IFilter right, ConditionOperator operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	
	public SimpleFilter(String variable, Object value) {
		this.left = new VariableFilter(variable);
		this.right = new ConstantFilter(value);
		this.operator = ConditionOperator.EQUAL;
	}

	@Override
	public String getString() {
		return operator.getValue().replace("%0", left.getString()).replace("%1", right.getString());
	}

	@Override
	public List<Object> getValues() {
		List<Object> ret = new ArrayList<>();
		ret.addAll(left.getValues());
		ret.addAll(right.getValues());
		return ret;
	}

	@Override
	public void setTableAlias(String alias) {
	}
}
