import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import bean.ReportBean;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

public class JasperMain {

  public static void main(String[] args) throws Exception {
      MyJasperGenerator gen = new MyJasperGenerator();

      //表示するデータを作る
      ReportBean bean = new ReportBean();

      //日本語を表示する場合は、別の設定が必要です。サンプルは英数字で確認してください。
      bean.setHogeStringValue("ほげもじれつ");
      bean.setHogeIntegerValue(123456);
      bean.setHogeBooleanValue(true);

      //JRDataSourceに詰める
      JRDataSource dataSource = gen.makeBeansDataSource(bean);

      //１で作ったjrxmlファイルのパス
      System.out.println(new File("src/main/resouces/BeanSample.jrxml").getAbsolutePath());
      String jrxmlFilePath = "src/main/resouces/BeanSample.jrxml";

      //jrxmlからコンパイルするjasperファイルの出力先
      String jasperFilePath= "BeanSample.jasper";

      //作成するpdfファイルの出力先
      String outputPdfPath= "BeanSample.pdf";

      Map<String,Object> params = new HashMap<String,Object>();//とりあえず今回は使いません。


      //作成実行
      gen.outputPdf(jrxmlFilePath, jasperFilePath, params, dataSource, outputPdfPath);
  }
}