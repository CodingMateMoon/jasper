package com.jasper.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CustomReport {

	public static void main(String[] args) {
		try {

			String rootPath = System.getProperty("user.dir");

			// rootPath : C:\Users\abc95\git\jasper\Jasper-Report
			// System.out.println(rootPath);

			String filePath = rootPath + "\\src\\main\\resources\\Student.jrxml";

			Subject subject1 = new Subject("Java", 80);
			Subject subject2 = new Subject("MySQL", 70);
			Subject subject3 = new Subject("PHP", 50);
			Subject subject4 = new Subject("MongoDB", 40);
			Subject subject5 = new Subject("C++", 60);

			List<Subject> list = new ArrayList<Subject>();
			list.add(subject1);
			list.add(subject2);
			list.add(subject3);
			list.add(subject4);
			list.add(subject5);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("studentName", "Moon");
			parameters.put("tableData", dataSource);
			parameters.put("subReport", getSubReport(rootPath + "\\src\\main\\resources\\FirstReport.jrxml"));
			parameters.put("subDataSource", getSubDataSource());
			parameters.put("subParameters", getSubParameters());

			JasperReport report = JasperCompileManager.compileReport(filePath);

			/*
			 * parameter로 JRBeanCollectionDataSource를 넘기기 때문에 fillReport 파라미터에서 dataSource
			 * 부분에 따로 넘길게 없는 경우 Empty dataSource로 넘깁니다.
			 */
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

			JasperExportManager.exportReportToPdfFile(print, rootPath + "\\src\\main\\resources\\data\\student.pdf");

			System.out.println("CustomReport Created...");

		} catch (Exception e) {
			System.out.println("Exception while creating report");
		}
	}

	public static JasperReport getSubReport(String filePath) {
		JasperReport report;
		try {
			report = JasperCompileManager.compileReport(filePath);
			return report;
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static JRBeanCollectionDataSource getSubDataSource() {
		Student student1 = new Student(1L, "Raj", "Joshi", "Happy Street", "Delhi");
		Student student2 = new Student(2L, "Peter", "Smith", "Any Street", "Mumbai");

		List<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		return dataSource;
	}

	public static Map<String, Object> getSubParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("studentName", "Raj");

		return parameters;
	}

}
