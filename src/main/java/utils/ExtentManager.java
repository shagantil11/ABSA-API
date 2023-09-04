package utils;

import java.io.File;
import java.util.Date;

import Constant.constant;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;


    public static ExtentReports getInstance() {


        if (extent == null) {
            Date d = new Date();
            String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";

            extent = new ExtentReports(constant.REPORT_PATH + fileName, true, DisplayOrder.NEWEST_FIRST);


            
            String reportFilename=constant.REPORT_FILE_NAME_PATH;
            
            extent.loadConfig(new File(reportFilename));
          
            extent.addSystemInfo("Selenium Version", "3.4.0").addSystemInfo(
                    "Environment", "QA");
        }
        return extent;
    }

}
