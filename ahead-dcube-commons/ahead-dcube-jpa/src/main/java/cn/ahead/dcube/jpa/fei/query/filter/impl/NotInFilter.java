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

package cn.ahead.dcube.jpa.fei.query.filter.impl;

import java.util.List;

import org.springframework.util.Assert;

import cn.ahead.dcube.jpa.fei.query.filter.operator.ConditionOperator;


/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 14:11 <br/>
 * Version:1.0 <br/>
 */
public class NotInFilter extends BaseFilter {

	private VariableFilter variable;

	private List<Object> values;

	public NotInFilter(String variable, List<Object> values) {
		Assert.notEmpty(values, "the params can not be empty");
		this.variable = new VariableFilter(variable);
		this.values = values;
	}

	@Override
	public String getString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < values.size(); i++) {
			sb.append("?,");
		}
		String placeHolder = sb.toString().substring(0, sb.length() - 1);
		return ConditionOperator.NOT_IN.getValue().replace("%0", variable.getString()).replace("%1", placeHolder);
	}

	@Override
	public List<Object> getValues() {
		return values;
	}

	@Override
	public void setTableAlias(String alias) {
		variable.setTableAlias(alias);
	}
}
