import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

public class JasperMain {

  public static void main(String[] args) throws Exception {
    Map<String, Object> parameters = new HashMap<>();
    JRCsvDataSource dataSource = new JRCsvDataSource(JasperMain.class.getResourceAsStream("data.csv"));
    dataSource.setUseFirstRowAsHeader(true);
    try (InputStream template = JasperMain.class.getResourceAsStream("example.jrxml")) {
      JasperReport report = JasperCompileManager.compileReport(template);
      JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
      JasperExportManager.exportReportToPdfFile(jasperPrint, "example.pdf");
    }
  }
}