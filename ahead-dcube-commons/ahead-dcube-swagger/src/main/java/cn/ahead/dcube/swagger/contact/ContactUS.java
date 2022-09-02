package cn.ahead.dcube.swagger.contact;

import lombok.Data;
import springfox.documentation.service.Contact;

@Data
public class ContactUS {

	private String name;
	private String url;
	private String email;

	public Contact convert() {
		return new Contact(name, url, email);
	}
}