package com.influxTest.service.measurement;

import java.util.concurrent.TimeUnit;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "cpu", database = "test", timeUnit = TimeUnit.SECONDS)
public class Cpu {
	

    @Column(name = "host")
    private String host;

    @Column(name = "value")
    private String value;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
    

}
