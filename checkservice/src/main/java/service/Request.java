package service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Request {

	private String name;
	private String city;
	private String country;
	private String street;
	private String param1;
	private String param2;
	private int plz;

	Request(Address address, String param1, String param2) {
		this.setName(address.getName()).setStreet(address.getStreet()).setCity(address.getCity())
				.setCountry(address.getCountry()).setParam1(param1).setParam2(param2);
	}
	
	public String toUrlParameterString(String charset) throws UnsupportedEncodingException {
		return URLEncoder.encode(getValuePair().toString(), charset);	
	}
	
	public NameValuePair[] getValuePair() {
		List<NameValuePair> nameValuePair = new ArrayList<>();
		for (Field field : this.getClass().getDeclaredFields()) {
			Object value = null;
			try {
				value = field.get(this);
			} catch (IllegalAccessException | IllegalArgumentException e) {
				throw new IllegalArgumentException(e);
			}
			
			if (value != null) {
				nameValuePair.add(new BasicNameValuePair(field.getName(), value.toString()));
			}

		}
		return nameValuePair.toArray(new NameValuePair[nameValuePair.size()]);
	}

	public String getName() {
		return name;
	}

	public Request setName(String name) {
		this.name = name;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Request setCity(String city) {
		this.city = city;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Request setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public Request setStreet(String street) {
		this.street = street;
		return this;
	}

	public int getPlz() {
		return plz;
	}

	public Request setPlz(int plz) {
		this.plz = plz;
		return this;
	}

	public String getParam1() {
		return param1;
	}

	public Request setParam1(String param1) {
		this.param1 = param1;
		return this;
	}

	public String getParam2() {
		return param2;
	}

	public Request setParam2(String param2) {
		this.param2 = param2;
		return this;
	}

}
