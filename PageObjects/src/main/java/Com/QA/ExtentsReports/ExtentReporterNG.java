package Com.QA.ExtentsReports;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(System.getProperty("user.dir") +"/reports/Extent.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	@SuppressWarnings("static-access")
	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				StringBuffer details = new StringBuffer();
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				Object[] instance = result.getParameters();
				for (int i = 0; i < instance.length; i++) {
					details.append(instance[i]);
					if (!(i == instance.length - 1)) {
						details.append(",");
					}
				}
				if (result.getThrowable() != null) {
					test.log(status, "Test Data - (" + details.toString() + ")");
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test Data - (" + details.toString() + ")");
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				if ((result.FAILURE > 0)) {
					test.log(LogStatus.FAIL, result.getMethod().getMethodName());
					test.log(LogStatus.FAIL, result.getThrowable());

					Screenshot.onTestFailure();
					test.log(LogStatus.FAIL, Screenshot.dest);

					System.out.println(Screenshot.dest);
					test.log(LogStatus.FAIL, test.addScreenCapture(Screenshot.dest));

				}
				if ((result.SUCCESS > 0)) {
					test.log(LogStatus.PASS, test.addScreenCapture("C:\\Users\\Kames\\Desktop\\Screenshots\\passed"
							+ result.getMethod().getMethodName() + ".png"));
					test.log(LogStatus.PASS, result.getMethod().getMethodName());
				}

				
				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
