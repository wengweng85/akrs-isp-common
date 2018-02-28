package com.epsoft.isp.webserivce.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
public class JaxbDateAdapter extends XmlAdapter<String, Date> {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	@Override
	public String marshal(Date date) throws Exception {
		return sdf.format(date);
	}

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		return sdf.parse(dateStr);
	}

}
