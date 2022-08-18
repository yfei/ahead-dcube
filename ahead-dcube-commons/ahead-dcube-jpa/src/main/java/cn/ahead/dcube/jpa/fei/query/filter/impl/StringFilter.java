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

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 14:11 <br/>
 * Version:1.0 <br/>
 */
public class StringFilter extends BaseFilter {

	private String condition;

	private List<Object> params = new ArrayList<Object>();

	public StringFilter(String condition) {
		this.condition = condition;
	}

	public StringFilter(String condition, List<Object> params) {
		this.condition = condition;
		this.params = params;
	}

	public StringFilter(String condition,Object... params){
		this.condition = condition;
		this.params = new ArrayList<Object>();
		for(Object param:params){
			this.params.add(param);
		}
	}

	@Override
	public String getString() {
		return condition;
	}

	@Override
	public List<Object> getValues() {
		return params;
	}

}
