package cn.dcube.ahead.jpa.fei.query.sort;

/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 14:54 <br/>
 * Version:1.0 <br/>
 */
public interface ISort {

	public ISort append(ISort sort);

	public String getString();

	public void setAlias(String alias);

}
