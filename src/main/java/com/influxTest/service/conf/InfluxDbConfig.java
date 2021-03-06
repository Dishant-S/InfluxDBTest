package com.influxTest.service.conf;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class InfluxDbConfig {

    @Value("${spring.influx.url:''}")
    private String influxDBUrl;

    @Value("${spring.influx.user:''}")
    private String userName;

    @Value("${spring.influx.password:''}")
    private String password;

    @Value("${spring.influx.database:''}")
    private String database;

    @Bean
    public InfluxDB influxDB(){
        InfluxDB influxDB = InfluxDBFactory.connect(influxDBUrl, userName, password);
        try {
            /**
             * Asynchronous insertion:
             * enableBatch Here first one is the number of points, the second is the time in milliseconds.
             * Point Number and time are used in combination if 100 or 2000 milliseconds are full
             * A write request is sent when either condition is met.
             */
            influxDB.setDatabase(database)
                    .enableBatch(100,2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            influxDB.setRetentionPolicy("autogen");
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }
}
