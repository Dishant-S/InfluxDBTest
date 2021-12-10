package com.influxTest.service.controller;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.influxTest.service.measurement.Cpu;

@RestController
public class InfluxTestController {
	
	@Autowired
    public InfluxDB influxDB;
	
	@GetMapping("/cpu")
	public String getCpu(){
		
		InfluxDBMapper influxDBMapper = new InfluxDBMapper(influxDB);
        List<Cpu> cpulist = influxDBMapper.query(Cpu.class);
        if(null!=cpulist) {
        	return cpulist.get(0).getHost() + " " + cpulist.get(0).getValue() +"\n " + cpulist.get(2).getHost() + " " + cpulist.get(2).getValue() ;
        }
		return null;
	}

}
