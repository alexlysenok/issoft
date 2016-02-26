package org.by.issoft.paramCollector;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import org.by.issoft.paramCollector.dataStorage.MemoryStorage;
import org.by.issoft.paramCollector.paramObtainers.DisksInfoObtainer;
import org.by.issoft.paramCollector.params.scalarParamValues.PhysicalMemoryUsageValue;
import org.by.issoft.paramCollector.params.tabularParamValues.DisksInfoValue;
import org.postgresql.util.PGobject;

import sun.util.logging.resources.logging_zh_HK;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Date date = new Date();
		Date date2 = new Date(System.currentTimeMillis());
		System.out.println(new Date(1220227200));
		System.out.println(new Date(1220832000));
		System.out.println(new Date(1221436800));
		System.out.println(new Date(4));
		System.out.println(new Date(5));
		System.out.println(new Date(6));

	}
}
